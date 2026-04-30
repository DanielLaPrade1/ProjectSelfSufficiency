package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp;

import java.util.List;

public record CropImportDTO(
        String name,
        String category,
        List<VarietyImportDTO> varieties
) {}
