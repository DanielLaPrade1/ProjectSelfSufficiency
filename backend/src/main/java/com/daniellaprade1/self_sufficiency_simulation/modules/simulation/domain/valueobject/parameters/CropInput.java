package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters;

import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.ValueRange;

import java.util.List;

public record CropInput(
        Double units,
        List<MetricValue> metrics,
        ValueRange yieldRange
) {}
