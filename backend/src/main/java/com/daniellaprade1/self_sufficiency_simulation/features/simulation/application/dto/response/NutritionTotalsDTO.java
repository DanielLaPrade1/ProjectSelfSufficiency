package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;

import java.util.Map;

public record NutritionTotalsDTO(
        Map<NutritionMetric, Double> totals
) {
    public double get(NutritionMetric metric) {
        return totals.getOrDefault(metric, 0.0);
    }
}
