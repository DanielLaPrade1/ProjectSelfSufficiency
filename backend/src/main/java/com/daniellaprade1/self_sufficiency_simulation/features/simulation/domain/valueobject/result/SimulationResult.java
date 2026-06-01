package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result;

import java.util.List;
import java.util.Map;

public record SimulationResult(
        Map<String, NutritionMetricResult> nutritionMetricResults,
        Double selfSufficiencyPercentage
) {}
