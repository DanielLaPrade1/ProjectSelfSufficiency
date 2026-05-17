package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MacroDistributionDTO (
   @NotNull(message = "proteinPercent is required")
   @Valid
   Double proteinPercent,

   @NotNull(message = "fatPercent is required")
   @Valid
   Double fatPercent,

   @NotNull(message = "carbsPercent is required")
   @Valid
   Double carbsPercent
){ }
