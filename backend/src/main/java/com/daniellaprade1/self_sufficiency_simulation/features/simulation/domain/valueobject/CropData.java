package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;

public record CropData(
        Double units,
        Nutrition nutrition,
        Yield yield
) {}
