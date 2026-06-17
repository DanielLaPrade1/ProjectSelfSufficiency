package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CropImportDTO(

        @NotBlank(message = "Crop name is required")
        String name,

        @NotBlank(message = "Crop species is required")
        String species,

        @NotEmpty(message = "At least one variety is required")
        @Valid
        List<VarietyImportDTO> varieties

) {}
