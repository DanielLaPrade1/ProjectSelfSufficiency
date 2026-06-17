package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.CropImportDTO;

import java.util.List;

public interface CropImportService {
    void importCropsFromJson(String path);
    void upsertCropTree(List<CropImportDTO> cropImportDTOList);
}
