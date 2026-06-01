package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.ValueRange;

public record NutritionMetricResult(
        ValueRange totals,
        Double target
) {}
