package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;

import java.util.function.ToDoubleFunction;

public enum NutritionMetric {
    CALORIES(Nutrition::getKcalPerGram),
    PROTEIN(Nutrition::getProteinPerGram),
    FAT(Nutrition::getTotalFatPerGram),
    CARBS(Nutrition::getTotalCarbsPerGram);

    private final ToDoubleFunction<Nutrition> value;

    NutritionMetric(ToDoubleFunction<Nutrition> value) {
        this.value = value;
    }

    public double extract(Nutrition crop) {
        return value.applyAsDouble(crop);
    }
}
