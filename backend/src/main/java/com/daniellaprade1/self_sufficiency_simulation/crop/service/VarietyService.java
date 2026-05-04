package com.daniellaprade1.self_sufficiency_simulation.crop.service;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.VarietyImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;

import java.util.List;

public interface VarietyService {
    Variety createOrUpdateVariety(Crop crop, VarietyImportDTO varietyImportDTO);
}
