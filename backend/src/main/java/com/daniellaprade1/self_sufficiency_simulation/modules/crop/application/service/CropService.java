package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Crop;

import java.util.List;

public interface CropService {
    List<CropOptionDTO> getAllCropOptions();
    Crop createOrUpdateCrop(CropImportDTO cropImportDTO);
}
