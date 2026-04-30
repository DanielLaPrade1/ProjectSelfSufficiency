package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VarietyProfileImportDTO(

        @NotNull(message = "kcalPerGram is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "kcalPerGram must be > 0")
        Float kcalPerGram,

        @NotNull(message = "yieldMinGrams is required")
        @Positive(message = "yieldMinGrams must be greater than 0")
        Double yieldMinGrams,

        @NotNull(message = "yieldMaxGrams is required")
        @Positive(message = "yieldMaxGrams must be greater than 0")
        Double yieldMaxGrams

) {
    @AssertTrue(message = "yieldMaxGrams must be greater than or equal to yieldMinGrams")
    public boolean isYieldRangeValid() {
        return yieldMaxGrams >= yieldMinGrams;
    }
}
