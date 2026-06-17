package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response.SimulationResponseDTO;

public interface SimulationService {
    SimulationResponseDTO runSimulation(SimulationRequestDTO request);
}
