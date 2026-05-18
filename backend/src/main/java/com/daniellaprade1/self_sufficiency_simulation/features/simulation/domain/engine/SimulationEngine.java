package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;

import java.util.List;

public interface SimulationEngine {
    SimulationResponseDTO run(List<CropData> cropData, MacroDistribution macroDistribution, Double calorieTarget);
}
