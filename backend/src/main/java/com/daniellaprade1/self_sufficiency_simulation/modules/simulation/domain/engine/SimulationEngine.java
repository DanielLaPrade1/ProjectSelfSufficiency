package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.engine;

import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result.SimulationResult;

public interface SimulationEngine {
    SimulationResult run(SimulationParameters parameters);
}
