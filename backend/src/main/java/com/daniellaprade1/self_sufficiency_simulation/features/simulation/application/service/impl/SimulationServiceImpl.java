package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.MacroDistributionRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.CropRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.service.SimulationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final VarietyRepository varietyRepository;
    private final SimulationEngine simulationEngine;

    public SimulationServiceImpl(VarietyRepository varietyRepository, SimulationEngine simulationEngine) {
        this.varietyRepository = varietyRepository;
        this.simulationEngine = simulationEngine;
    }

    @Override
    public SimulationResponseDTO runSimulation(SimulationRequestDTO request) {
        List<CropData> cropData = request.cropRequests()
                .stream()
                .map(this::toCropData).toList();

        MacroDistributionRequestDTO macroDistributionRequest = request.macroDistribution();
        MacroDistribution macroDistribution = this.resolveMacroDistribution(macroDistributionRequest);

        return simulationEngine.run(cropData, macroDistribution, request.calorieTarget());
    }

    @Override
    public CropData toCropData(CropRequestDTO cropRequest) {
        Variety variety = varietyRepository.findById(cropRequest.varietyId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid VarietyID"));

        Nutrition varietyNutrition = variety.getProfile().getNutrition();
        Yield varietyYield = variety.getProfile().getYield();

        return new CropData(
                cropRequest.units(),
                varietyNutrition.getKcalPerGram(),
                varietyNutrition.getProteinPerGram(),
                varietyNutrition.getTotalFatPerGram(),
                varietyNutrition.getTotalCarbsPerGram(),
                varietyYield.getMinGrams(),
                varietyYield.getMaxGrams()
        );
    }

    public MacroDistribution resolveMacroDistribution(
            MacroDistributionRequestDTO macroDistributionRequest
    ) {
        if (macroDistributionRequest.isPresetMode()) return macroDistributionRequest.preset().getDistribution();
        else {
            return new MacroDistribution(
                    macroDistributionRequest.customDistribution().proteinPercent(),
                    macroDistributionRequest.customDistribution().fatPercent(),
                    macroDistributionRequest.customDistribution().carbsPercent()
            );
        }
    }
}
