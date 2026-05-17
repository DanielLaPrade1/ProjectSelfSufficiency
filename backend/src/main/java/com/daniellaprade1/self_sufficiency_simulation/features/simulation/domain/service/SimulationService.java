package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.service;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.CropInputDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;

public interface SimulationService {
    SimulationResponseDTO runSimulation(SimulationRequestDTO request);
    SimulationCropData toSimulationCropData(CropInputDTO cropInputDTO);
}
