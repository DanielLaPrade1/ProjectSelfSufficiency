package com.daniellaprade1.self_sufficiency_simulation.simulation.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.repository.CropProfileRepository;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.service.SimulationService;
import org.springframework.stereotype.Service;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final CropProfileRepository cropProfileRepository;

    public SimulationServiceImpl(CropProfileRepository cropProfileRepository) {
        this.cropProfileRepository = cropProfileRepository;
    }

    // Unimplemented
    @Override
    public SimulationResponseDTO runSimulation(SimulationRequestDTO request) {
        // SimulationRequestDTO + CropProfile -> SimulationCropData
        // SimulationCropData -> SimulationEngine
        return null;
    }
}
