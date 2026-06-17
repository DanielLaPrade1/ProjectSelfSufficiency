package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.mapper;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.valueobject.Nutrition;
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
