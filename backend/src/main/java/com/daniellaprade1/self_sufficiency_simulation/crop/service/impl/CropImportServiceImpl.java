package com.daniellaprade1.self_sufficiency_simulation.crop.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.service.CropImportService;
import com.daniellaprade1.self_sufficiency_simulation.infra.importer.JsonCropImporter;

import java.util.List;

public class CropImportServiceImpl implements CropImportService {

    private final JsonCropImporter importer;

    public CropImportServiceImpl(JsonCropImporter importer) {
        this.importer = importer;
    }

    @Override
    public void importFromJson(String path) {
        List<CropImportDTO> crops = importer.loadFromClasspath("data/crops.json");
    }
}
