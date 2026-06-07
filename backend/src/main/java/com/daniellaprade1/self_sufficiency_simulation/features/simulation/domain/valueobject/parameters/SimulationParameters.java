package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters;

import java.util.List;

public record SimulationParameters(
        List<CropInput> cropInputs,
        MacroDistributionInput macroDistributionInput,
        Double dailyCalorieTarget,
        Double simulationLengthDays
) {}
