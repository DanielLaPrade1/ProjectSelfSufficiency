package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.mapper;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import org.springframework.stereotype.Component;

@Component
public class NutritionMapper {

    public Nutrition toEmbeddable(NutritionDTO dto) {
        Nutrition nutrition = new Nutrition();
        nutrition.setKcalPerGram(dto.kcalPerGram());
        nutrition.setProteinPerGram(dto.proteinPerGram());
        nutrition.setTotalFatPerGram(dto.totalFatPerGram());
        nutrition.setTotalCarbsPerGram(dto.totalCarbsPerGram());
        return nutrition;
    }
}
