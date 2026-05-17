package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.importer;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.service.CropImportService;
import org.springframework.boot.CommandLineRunner;

//@Component
public class CropDataLoader implements CommandLineRunner {

    private final CropImportService cropImportService;

    public CropDataLoader(CropImportService cropImportService) {
        this.cropImportService = cropImportService;
    }

    @Override
    public void run(String... args) {
        cropImportService.importCropsFromJson("/data/crops.json");
    }
}
