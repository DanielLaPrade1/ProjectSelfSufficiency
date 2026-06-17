package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request;

import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.dto.MacroDistributionRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record SimulationRequestDTO(

        @NotNull(message = "calorieTarget is required")
        @Min(value = 800, message = "calorieTarget must be greater than 800")
        Double calorieTarget,

        @Valid
        @Positive(message = "simulationLengthDays must be greater than 0")
        Double simulationLengthDays,

        @NotNull(message = "macroDistribution is required")
        @Valid
        MacroDistributionRequestDTO macroDistribution,

        @NotEmpty(message = "cropRequests cannot be empty")
        @Valid
        List<CropRequestDTO> cropRequests
) {}