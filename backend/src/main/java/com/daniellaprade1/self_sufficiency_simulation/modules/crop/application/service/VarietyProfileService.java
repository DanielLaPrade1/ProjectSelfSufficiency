package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.YieldDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;

public interface VarietyProfileService {
    void createOrUpdateVarietyProfile(
            Variety variety,
            NutritionDTO nutrition,
            YieldDTO yield
    );
}
