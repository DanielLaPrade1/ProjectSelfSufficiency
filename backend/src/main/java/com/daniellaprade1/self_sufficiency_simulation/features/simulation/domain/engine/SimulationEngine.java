package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.SimulationParameters;

import java.util.List;

public interface SimulationEngine {
    SimulationResponseDTO run(SimulationParameters parameters);
}
