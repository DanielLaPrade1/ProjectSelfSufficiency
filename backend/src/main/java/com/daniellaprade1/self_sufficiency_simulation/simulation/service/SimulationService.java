package com.daniellaprade1.self_sufficiency_simulation.simulation.service;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.CropInputDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;

public interface SimulationService {
    SimulationResponseDTO runSimulation(SimulationRequestDTO request);
    SimulationCropData toSimulationCropData(CropInputDTO cropInputDTO);
}
