package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.service.MacroService;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroDistributionRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.CropRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.service.SimulationService;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.SimulationParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final VarietyRepository varietyRepository;
    private final SimulationEngine simulationEngine;

    private final MacroService macroService;

    public SimulationServiceImpl(VarietyRepository varietyRepository, SimulationEngine simulationEngine, MacroService macroService) {
        this.varietyRepository = varietyRepository;
        this.simulationEngine = simulationEngine;
        this.macroService = macroService;
    }

    @Override
    public SimulationResponseDTO runSimulation(SimulationRequestDTO request) {


        List<CropData> cropData = request.cropRequests()
                .stream()
                .map(this::toCropData)
                .toList();

        MacroDistributionRequestDTO macroDistributionRequest = request.macroDistribution();
        MacroDistribution macroDistribution = macroService.resolveMacroDistribution(macroDistributionRequest);

        SimulationParameters simulationParameters =
                new SimulationParameters(
                        cropData,
                        macroDistribution,
                        request.calorieTarget()
                );

        return simulationEngine.run(simulationParameters);
    }

    @Override
    public CropData toCropData(CropRequestDTO cropRequest) {
        Variety variety = varietyRepository.findById(cropRequest.varietyId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid VarietyID"));

        Nutrition requestVarietyNutrition = variety.getProfile().getNutrition();
        Yield requestVarietyYield = variety.getProfile().getYield();

        return new CropData(
                cropRequest.units(),
                requestVarietyNutrition,
                requestVarietyYield
        );
    }
}
