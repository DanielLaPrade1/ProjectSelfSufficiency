package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;

import java.util.Map;

public record SimulationResult(
        NutritionMetricResult nutritionResults,
        Double selfSufficiencyPercentage
) {}
