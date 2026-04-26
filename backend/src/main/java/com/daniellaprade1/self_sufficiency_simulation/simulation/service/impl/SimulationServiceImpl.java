package com.daniellaprade1.self_sufficiency_simulation.simulation.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyProfileRepository;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.service.SimulationService;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final VarietyProfileRepository varietyProfileRepository;

    public SimulationServiceImpl(VarietyProfileRepository varietyProfileRepository) {
        this.varietyProfileRepository = varietyProfileRepository;
    }

    // Unimplemented
    @Override
    public SimulationResponseDTO runSimulation(SimulationRequestDTO request) {
        // SimulationRequestDTO + CropProfile -> SimulationCropData
        // SimulationCropData -> SimulationEngine
        return null;
    }
}
