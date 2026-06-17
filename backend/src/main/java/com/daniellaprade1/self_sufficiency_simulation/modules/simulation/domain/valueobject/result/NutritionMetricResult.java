package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result;

import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.ValueRange;

public record NutritionMetricResult(
        ValueRange totals,
        Double target
) {}
