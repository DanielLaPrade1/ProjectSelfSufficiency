package com.daniellaprade1.self_sufficiency_simulation.crop.domain.mapper;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Nutrition;
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
