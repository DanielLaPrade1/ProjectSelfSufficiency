package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto;


import jakarta.validation.constraints.*;
import java.util.UUID;

public record CropOptionDTO(

        @NotNull(message = "varietyID is required")
        UUID varietyID,

        @NotBlank(message = "cropName is required")
        @Size(max = 100, message = "cropName must be less than 100 characters")
        String cropName,

        @NotBlank(message = "species is required")
        @Size(max = 150, message = "species must be less than 150 characters")
        String species,

        @NotBlank(message = "varietyName is required")
        @Size(max = 100, message = "varietyName must be less than 100 characters")
        String varietyName,

        @NotNull(message = "kcalPerGram is required")
        @Positive(message = "kcalPerGram must be greater than 0")
        Float kcalPerGram,

        @NotNull(message = "yieldMinGrams is required")
        @PositiveOrZero(message = "yieldMinGrams must be >= 0")
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
