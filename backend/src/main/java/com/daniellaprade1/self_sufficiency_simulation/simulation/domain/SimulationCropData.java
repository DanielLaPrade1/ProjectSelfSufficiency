package com.daniellaprade1.self_sufficiency_simulation.simulation.domain;

public record SimulationCropData(
        Double landArea,
        Double kcalPerGram,
        Double yieldMin,
        Double yieldMax
) {}
