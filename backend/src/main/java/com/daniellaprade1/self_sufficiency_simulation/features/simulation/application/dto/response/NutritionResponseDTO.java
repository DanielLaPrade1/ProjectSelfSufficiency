package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.ValueRange;

import java.util.Map;

public record NutritionResponseDTO(
        Map<NutritionMetric, ValueRange> totals,
        Map<NutritionMetric, Double> targets
) {
    public ValueRange getTotal(NutritionMetric metric) {
        return totals.get(metric);
    }
    public double getTarget(NutritionMetric metric) {
        return targets.get(metric);
    }
}
