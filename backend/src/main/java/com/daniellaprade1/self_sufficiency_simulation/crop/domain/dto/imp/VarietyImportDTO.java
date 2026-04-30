package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VarietyImportDTO(

        @NotBlank(message = "Variety name is required")
        String name,

        @NotNull(message = "varietyProfile is required")
        @Valid
        VarietyProfileImportDTO varietyProfile

) {}
