package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto;

import java.util.Map;

public record NutritionTotals(
        Map<NutritionMetric, Double> totals
) {
    public double get(NutritionMetric metric) {
        return totals.getOrDefault(metric, 0.0);
    }
}
