package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record YieldDTO(
        @NotNull(message = "minGrams is required")
        @Positive(message = "minGrams must be greater than 0")
        Double minGrams,

        @NotNull(message = "maxGrams is required")
        @Positive(message = "maxGrams must be greater than 0")
        Double maxGrams
) {
    @AssertTrue(message = "maxGrams must be greater than or equal to minGrams")
    public boolean isYieldRangeValid() {
        return maxGrams >= minGrams;
    }
}
