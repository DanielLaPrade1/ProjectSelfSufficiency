package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CropInputDTO(

        @NotNull(message = "varietyId is required")
        UUID varietyId,

        @NotNull(message = "units is required")
        @Positive(message = "units must be greater than 0")
        Double units

) {}
