package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.mapper.input;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MetricValue;

import java.util.Arrays;
import java.util.List;

public class NutritionMetricMapper {
    public static List<MetricValue> toMetricValues(Nutrition nutrition) {
        return Arrays.stream(NutritionMetric.values())
                .map(m -> new MetricValue(m.name(), m.extract(nutrition)))
                .toList();
    }
}
