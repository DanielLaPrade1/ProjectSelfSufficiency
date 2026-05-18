package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record MacroDistributionDTO (
   @NotNull(message = "name is required")
   @Valid
   String name,

   @NotNull(message = "proteinPct is required")
   @Valid
   Double proteinPct,

   @NotNull(message = "fatPct is required")
   @Valid
   Double fatPct,

   @NotNull(message = "carbsPct is required")
   @Valid
   Double carbsPct
){ }
