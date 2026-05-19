package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;

import java.util.Map;

public record NutritionResponseDTO(
        Map<NutritionMetric, Double> totals,
        Map<NutritionMetric, Double> targets
) {
    public double getTotal(NutritionMetric metric) {
        return totals.getOrDefault(metric, 0.0);
    }
    public double getTarget(NutritionMetric metric) {
        return targets.getOrDefault(metric, 0.0);
    }
}
