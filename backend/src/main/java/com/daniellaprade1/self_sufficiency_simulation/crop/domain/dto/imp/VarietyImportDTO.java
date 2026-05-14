package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.YieldDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VarietyImportDTO(

        @NotBlank(message = "name is required")
        String name,

        @NotNull(message = "nutrition is required")
        @Valid
        NutritionDTO nutrition,

        @NotNull(message = "yield is required")
        YieldDTO yield
) {}
