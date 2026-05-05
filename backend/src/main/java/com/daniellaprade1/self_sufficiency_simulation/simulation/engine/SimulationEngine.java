package com.daniellaprade1.self_sufficiency_simulation.simulation.engine;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;

import java.util.List;

public interface SimulationEngine {
    SimulationResponseDTO run(List<SimulationCropData> cropData, Double calorieTarget);
}
