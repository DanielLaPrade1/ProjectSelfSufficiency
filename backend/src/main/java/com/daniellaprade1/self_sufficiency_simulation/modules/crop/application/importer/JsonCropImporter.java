package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.importer;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.CropImportDTO;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class JsonCropImporter {

    private final CropJsonParser parser;

    public JsonCropImporter(CropJsonParser parser) {
        this.parser = parser;
    }

    public List<CropImportDTO> loadFromClasspath(String path) {
        try (InputStream is = getClass().getResourceAsStream(path)) {
            if (is == null) {
                throw new RuntimeException("File not found: " + path);
            }
            return parser.parse(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
