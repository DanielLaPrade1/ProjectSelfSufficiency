package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.ValueRange;


public class YieldRangeMapper {
    public static ValueRange toValueRange(Yield yield) {
        return new ValueRange(yield.getMinGrams(), yield.getMaxGrams());
    }
}
