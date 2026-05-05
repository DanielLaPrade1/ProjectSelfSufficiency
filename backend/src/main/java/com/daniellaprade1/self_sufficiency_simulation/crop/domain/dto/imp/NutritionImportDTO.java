package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record NutritionImportDTO(
        @NotNull(message = "kcalPerGram is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "kcalPerGram must be > 0")
        Double kcalPerGram
) {}
