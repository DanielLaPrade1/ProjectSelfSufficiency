package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto;

public record MacroPresetResponseDTO (
        String name,
        double proteinPct,
        double fatPct,
        double carbsPct
) {}
