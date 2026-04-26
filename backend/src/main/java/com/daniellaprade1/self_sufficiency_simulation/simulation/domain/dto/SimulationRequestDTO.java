package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto;

import java.util.List;

public record SimulationRequestDTO(
        double CalorieTarget,
        List<CropInputDTO> cropInputs
) {}
