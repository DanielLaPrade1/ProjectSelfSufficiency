package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters;

import java.util.List;

public record SimulationParameters(
        List<CropData> cropData,
        MacroDistribution macroDistribution,
        Double dailyCalorieTarget
) {}
