package com.daniellaprade1.self_sufficiency_simulation.infra.loader;

import com.daniellaprade1.self_sufficiency_simulation.crop.service.CropImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
