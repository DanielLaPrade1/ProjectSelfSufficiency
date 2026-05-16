package com.daniellaprade1.self_sufficiency_simulation.simulation.domain;

public record SimulationCropData(
        Double units,
        Double kcalPerGram,
        Double proteinPerGram,
        Double totalFatPerGram,
        Double totalCarbsPerGram,
        Double yieldMin,
        Double yieldMax
) {}
