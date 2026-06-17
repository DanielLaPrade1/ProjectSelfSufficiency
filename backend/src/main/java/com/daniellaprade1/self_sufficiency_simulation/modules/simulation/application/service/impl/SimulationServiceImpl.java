package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.service.MacroService;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.dto.MacroDistributionRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input.CropInputMapper;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input.MacroDistributionInputMapper;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.output.SimulationResponseMapper;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.CropInput;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.CropRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.service.SimulationService;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.MacroDistributionInput;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result.SimulationResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.toMap;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final VarietyRepository varietyRepository;
    private final SimulationEngine simulationEngine;

    private final MacroService macroService;

    private final CropInputMapper cropInputMapper;
    private final MacroDistributionInputMapper macroDistributionInputMapper;

    private final SimulationResponseMapper simulationResponseMapper;


    public SimulationServiceImpl(
            VarietyRepository varietyRepository,
            SimulationEngine simulationEngine,
            MacroService macroService,
            CropInputMapper cropInputMapper,
            MacroDistributionInputMapper macroDistributionInputMapper,
            SimulationResponseMapper simulationResponseMapper
    ) {
        this.varietyRepository = varietyRepository;
        this.simulationEngine = simulationEngine;
        this.macroService = macroService;

        this.cropInputMapper = cropInputMapper;
        this.macroDistributionInputMapper = macroDistributionInputMapper;

        this.simulationResponseMapper = simulationResponseMapper;
    }

    @Override
    public SimulationResponseDTO runSimulation(SimulationRequestDTO request) {

        // Fetch Varieties
        List<UUID> varietyIds = request.cropRequests()
                .stream()
                .map(CropRequestDTO::varietyId)
                .toList();

        Map<UUID, Variety> varietyMap = varietyRepository.findAllById(varietyIds)
                .stream()
                .collect(toMap(Variety::getId, v -> v));



        // CropRequestDTO -> Parameter: cropInputs
        List<CropInput> cropInputs = request.cropRequests()
                .stream()
                .map(cropRequest -> {
                    Variety variety = varietyMap.get(cropRequest.varietyId());
                    if (variety == null) throw new IllegalArgumentException("Invalid VarietyID: " + cropRequest.varietyId());
                    return cropInputMapper.toCropInput(variety, cropRequest.units());
                })
                .toList();

        // MacroDistributionDTO -> Parameter: macroDistributionInput
        MacroDistributionRequestDTO macroDistributionRequest = request.macroDistribution();
        MacroDistribution macroDistribution = macroService.resolveMacroDistribution(macroDistributionRequest);
        MacroDistributionInput macroDistributionInput = macroDistributionInputMapper.toMacroDistributionInput(macroDistribution);

        // Run simulation engine
        SimulationParameters simulationParameters =
                new SimulationParameters(
                        cropInputs,
                        macroDistributionInput,
                        request.calorieTarget(),
                        request.simulationLengthDays()
                );
        SimulationResult result = simulationEngine.run(simulationParameters);

        // SimulationResult -> SimulationResponseDTO
        return simulationResponseMapper.toNutritionResponseDTO(result);
    }
}
