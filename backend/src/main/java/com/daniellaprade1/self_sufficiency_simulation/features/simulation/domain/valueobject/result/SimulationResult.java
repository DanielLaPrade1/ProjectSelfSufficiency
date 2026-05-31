package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result;

import java.util.List;

public record SimulationResult(
        List<NutritionMetricResult> nutritionMetricResults,
        Double selfSufficiencyPercentage
) {}
