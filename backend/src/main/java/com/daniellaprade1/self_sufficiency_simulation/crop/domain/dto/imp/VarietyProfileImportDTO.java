package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp;

public record VarietyProfileImportDTO(
        float kcalPerGram,
        int yieldMinGrams,
        int yieldMaxGrams
) { }
