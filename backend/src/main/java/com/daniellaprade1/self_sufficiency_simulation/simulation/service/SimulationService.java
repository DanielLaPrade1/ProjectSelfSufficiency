package com.daniellaprade1.self_sufficiency_simulation.simulation.service;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;

public interface SimulationService {
    public SimulationResponseDTO runSimulation(SimulationRequestDTO request);
}
