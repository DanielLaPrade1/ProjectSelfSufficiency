package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.CropInputDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.service.SimulationService;
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
        List<SimulationCropData> cropData = request.cropInputs()
                .stream()
                .map(this::toSimulationCropData).toList();

        return simulationEngine.run(cropData, request.calorieTarget());
    }

    @Override
    public SimulationCropData toSimulationCropData(CropInputDTO input) {
        Variety variety = varietyRepository.findById(input.varietyId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid VarietyID"));

        Nutrition varietyNutrition = variety.getProfile().getNutrition();
        Yield varietyYield = variety.getProfile().getYield();

        return new SimulationCropData(
                input.units(),
                varietyNutrition.getKcalPerGram(),
                varietyNutrition.getProteinPerGram(),
                varietyNutrition.getTotalFatPerGram(),
                varietyNutrition.getTotalCarbsPerGram(),
                varietyYield.getMinGrams(),
                varietyYield.getMaxGrams()
        );

    }
}
