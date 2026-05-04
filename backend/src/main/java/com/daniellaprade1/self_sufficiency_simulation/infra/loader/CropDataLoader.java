package com.daniellaprade1.self_sufficiency_simulation.infra.loader;

import com.daniellaprade1.self_sufficiency_simulation.crop.service.CropImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CropDataLoader implements CommandLineRunner {

    private final CropImportService service;

    public CropDataLoader(CropImportService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        service.importCropsFromJson("/data/crops.json");
    }
}
