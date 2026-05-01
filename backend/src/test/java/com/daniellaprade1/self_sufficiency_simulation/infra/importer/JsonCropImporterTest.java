package com.daniellaprade1.self_sufficiency_simulation.infra.importer;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.NutritionImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.VarietyImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.YieldImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.infra.parser.CropJsonParser;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JsonCropImporterTest {

    private final JsonCropImporter importer;

    JsonCropImporterTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        CropJsonParser parser = new CropJsonParser(objectMapper);
        this.importer = new JsonCropImporter(parser);
    }


    @Test
    void shouldLoadAndParseJsonFromClasspath() {
        List<CropImportDTO> result = importer.loadFromClasspath("/data/crops_test.json");

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();

        // Crop
        CropImportDTO potato = result.getFirst();
        assertThat(potato.name()).isEqualTo("Potato");
        assertThat(potato.species()).isEqualTo("Solanum tuberosum");
        assertThat(potato.varieties()).isNotEmpty();

        // Variety
        VarietyImportDTO gold = potato.varieties().getFirst();
        assertThat(gold.name()).isEqualTo("Gold");

        VarietyImportDTO red = potato.varieties().get(1);
        assertThat(red.name()).isEqualTo("Red");

        VarietyImportDTO russet = potato.varieties().get(2);
        assertThat(russet.name()).isEqualTo("Russet");


        // Variety Profiles (nutrition and yield)
        NutritionImportDTO goldNutrition = gold.nutrition();
        YieldImportDTO goldYield = gold.yield();

        assertThat(goldNutrition.kcalPerGram()).isEqualTo(0.75f);
        assertThat(goldYield.minGrams()).isEqualTo(1134);
        assertThat(goldYield.maxGrams()).isEqualTo(1814);

        NutritionImportDTO redNutrition = red.nutrition();
        YieldImportDTO redYield = red.yield();

        assertThat(redNutrition.kcalPerGram()).isEqualTo(0.73f);
        assertThat(redYield.minGrams()).isEqualTo(907);
        assertThat(redYield.maxGrams()).isEqualTo(1588);

    }
}