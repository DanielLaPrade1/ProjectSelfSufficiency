package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record NutritionDTO(
        @NotNull(message = "kcalPerGram is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "kcalPerGram must be > 0")
        Double kcalPerGram,

        @NotNull(message = "proteinPerGram is required")
        @DecimalMin(value = "0.0", message = "proteinPerGram must be >= 0")
        Double proteinPerGram,

        @NotNull(message = "totalFatPerGram is required")
        @DecimalMin(value = "0.0", message = "totalFatPerGram must be >= 0")
        Double totalFatPerGram,

        @NotNull(message = "totalCarbsPerGram is required")
        @DecimalMin(value = "0.0", message = "totalCarbsPerGram must be >= 0")
        Double totalCarbsPerGram
) {}
