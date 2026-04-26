package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto;

import java.util.UUID;

public record CropInputDTO(
        UUID varietyId,
        double landArea
) {}
