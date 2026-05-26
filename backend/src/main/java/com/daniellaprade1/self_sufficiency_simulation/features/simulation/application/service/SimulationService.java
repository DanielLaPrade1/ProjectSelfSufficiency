package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.service;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.CropRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;

public interface SimulationService {
    SimulationResponseDTO runSimulation(SimulationRequestDTO request);
    CropData toCropData(CropRequestDTO cropRequestDTO);
}
