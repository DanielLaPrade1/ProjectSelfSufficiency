package com.daniellaprade1.self_sufficiency_simulation.crop.service;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;

import java.util.List;
import java.util.UUID;

public interface CropService {
    List<CropOptionDTO> getAllCropOptions();

    Crop createOrUpdateCrop(CropImportDTO cropImportDTO);

}
