package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.VarietyImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.CropImportService;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.CropService;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.VarietyProfileService;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.VarietyService;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.importer.JsonCropImporter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropImportServiceImpl implements CropImportService {

    private final JsonCropImporter importer;

    private final CropService cropService;
    private final VarietyService varietyService;
    private final VarietyProfileService varietyProfileService;

    public CropImportServiceImpl(
            JsonCropImporter importer,
            CropService cropService,
            VarietyService varietyService,
            VarietyProfileService varietyProfileService
    ) {
        this.importer = importer;
        this.cropService = cropService;
        this.varietyService = varietyService;
        this.varietyProfileService = varietyProfileService;
    }

    @Override
    public void importCropsFromJson(String path) {
        List<CropImportDTO> crops = importer.loadFromClasspath(path);
        upsertCropTree(crops);
    }

    // Optimize
    public void upsertCropTree(List<CropImportDTO> cropImportDTOs) {
        for (CropImportDTO cropImportDTO : cropImportDTOs) {
            Crop crop = cropService.createOrUpdateCrop(cropImportDTO);
            for (VarietyImportDTO varietyImportDTO: cropImportDTO.varieties()) {
                Variety variety = varietyService.createOrUpdateVariety(crop, varietyImportDTO);
                varietyProfileService.createOrUpdateVarietyProfile(
                        variety,
                        varietyImportDTO.nutrition(),
                        varietyImportDTO.yield()
                );
            }
        }
    }


}
