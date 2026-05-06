package com.daniellaprade1.self_sufficiency_simulation.simulation.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.CropInputDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.simulation.service.SimulationService;
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

        return new SimulationCropData(
                input.units(),
                variety.getProfile().getKcalPerGram(),
                variety.getProfile().getYieldMinGrams(),
                variety.getProfile().getYieldMaxGrams()
        );

    }
}
