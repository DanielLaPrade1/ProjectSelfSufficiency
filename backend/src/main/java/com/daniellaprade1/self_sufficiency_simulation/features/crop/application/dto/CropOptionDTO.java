package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto;


import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import jakarta.validation.constraints.*;

import java.util.UUID;

public record CropOptionDTO(

        @NotBlank(message = "varietyID is required")
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

        @NotBlank(message = "varietyImage is required")
        String varietyImageUrl,

        @NotNull(message = "nutrition is required")
        Nutrition nutrition,

        @NotNull(message = "yield is required")
        Yield yield
) {}
