package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;

import java.util.function.ToDoubleFunction;

public enum NutritionMetric {
    CALORIES(SimulationCropData::kcalPerGram),
    PROTEIN(SimulationCropData::proteinPerGram),
    FAT(SimulationCropData::totalFatPerGram),
    CARBS(SimulationCropData::totalCarbsPerGram);

    private final ToDoubleFunction<SimulationCropData> extractor;

    NutritionMetric(ToDoubleFunction<SimulationCropData> extractor) {
        this.extractor = extractor;
    }

    public double extract(SimulationCropData crop) {
        return extractor.applyAsDouble(crop);
    }
}
