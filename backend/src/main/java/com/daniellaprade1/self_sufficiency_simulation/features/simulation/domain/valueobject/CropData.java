package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject;

public record CropData(
        Double units,
        Double kcalPerGram,
        Double proteinPerGram,
        Double totalFatPerGram,
        Double totalCarbsPerGram,
        Double yieldMin,
        Double yieldMax
) {}
