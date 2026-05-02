package com.daniellaprade1.self_sufficiency_simulation.crop.service;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.NutritionImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.YieldImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;

public interface VarietyProfileService {
    void createOrUpdateVarietyProfile(
            Variety variety,
            NutritionImportDTO nutritionImportDTO,
            YieldImportDTO yieldImportDTO
    );
}
