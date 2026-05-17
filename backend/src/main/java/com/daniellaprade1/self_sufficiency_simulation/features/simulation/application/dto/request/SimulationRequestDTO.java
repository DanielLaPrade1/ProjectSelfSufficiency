package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record SimulationRequestDTO(

        @NotNull(message = "calorieTarget is required")
        @Positive(message = "calorieTarget must be greater than 0")
        Double calorieTarget,

        @NotEmpty(message = "cropInputs cannot be empty")
        @Valid
        List<CropInputDTO> cropInputs

) {}