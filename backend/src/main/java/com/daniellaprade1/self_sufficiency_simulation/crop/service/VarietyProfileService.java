package com.daniellaprade1.self_sufficiency_simulation.crop.service;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.YieldDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;

public interface VarietyProfileService {
    void createOrUpdateVarietyProfile(
            Variety variety,
            NutritionDTO nutrition,
            YieldDTO yield
    );
}
