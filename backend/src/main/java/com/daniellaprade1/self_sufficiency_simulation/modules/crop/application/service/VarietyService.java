package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.VarietyImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;

public interface VarietyService {
    Variety createOrUpdateVariety(Crop crop, VarietyImportDTO varietyImportDTO);
}
