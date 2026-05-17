package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.enums;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;

import java.util.function.ToDoubleFunction;

public enum NutritionMetric {
    CALORIES(CropData::kcalPerGram),
    PROTEIN(CropData::proteinPerGram),
    FAT(CropData::totalFatPerGram),
    CARBS(CropData::totalCarbsPerGram);

    private final ToDoubleFunction<CropData> extractor;

    NutritionMetric(ToDoubleFunction<CropData> extractor) {
        this.extractor = extractor;
    }

    public double extract(CropData crop) {
        return extractor.applyAsDouble(crop);
    }
}
