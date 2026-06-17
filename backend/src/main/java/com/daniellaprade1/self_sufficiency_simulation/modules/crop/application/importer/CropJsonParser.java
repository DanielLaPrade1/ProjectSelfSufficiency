package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.importer;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.CropImportDTO;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class CropJsonParser {

    private final ObjectMapper objectMapper;

    public CropJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<CropImportDTO> parse(InputStream inputStream) {
        return objectMapper.readValue(
                inputStream,
                new TypeReference<List<CropImportDTO>>() {}
        );
    }
}
