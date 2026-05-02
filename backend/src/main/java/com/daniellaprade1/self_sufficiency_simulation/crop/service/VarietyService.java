package com.daniellaprade1.self_sufficiency_simulation.crop.service;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.VarietyImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;

public interface VarietyService {
    Variety createOrUpdateVariety(Crop crop, VarietyImportDTO varietyImportDTO);
}
