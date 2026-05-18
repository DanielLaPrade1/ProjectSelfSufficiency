package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.MacroDistributionPreset;
import jakarta.validation.constraints.AssertTrue;


public record MacroDistributionRequestDTO (
        MacroDistributionPreset preset,
        MacroDistributionDTO customDistribution
){
    @AssertTrue(message = "Either preset or customDistribution must be provided, but not both")
    public boolean isValid() {
        return (preset == null) ^ (customDistribution == null);
    }

    public boolean isPresetMode() { return preset != null; }

    public boolean isCustomMode() {
        return customDistribution != null;
    }
}
