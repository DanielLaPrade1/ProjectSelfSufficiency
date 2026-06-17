package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result;

import java.util.Map;

public record SimulationResult(
        Map<String, NutritionMetricResult> nutritionMetricResults,
        Double selfSufficiencyPercentage
) {}
