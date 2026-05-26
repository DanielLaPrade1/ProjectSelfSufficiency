package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result;

public record NutritionMetricResult(
        String metricName,
        Double total,
        Double target
) {}
