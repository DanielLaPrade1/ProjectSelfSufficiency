package com.daniellaprade1.self_sufficiency_simulation.crop.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.NutritionImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.YieldImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.VarietyProfile;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyProfileRepository;
import com.daniellaprade1.self_sufficiency_simulation.crop.service.VarietyProfileService;

public class VarietyProfileServiceImpl implements VarietyProfileService {

    private final VarietyProfileRepository varietyProfileRepository;

    public VarietyProfileServiceImpl(VarietyProfileRepository varietyProfileRepository) {
        this.varietyProfileRepository = varietyProfileRepository;
    }

    @Override
    public void createOrUpdateVarietyProfile(
            Variety variety,
            NutritionImportDTO nutritionImportDTO,
            YieldImportDTO yieldImportDTO
    ) {
        varietyProfileRepository.findByVarietyId(variety.getId())
                .ifPresentOrElse(existing -> {
                    // UPDATE
                    existing.setKcalPerGram(nutritionImportDTO.kcalPerGram());
                    existing.setYieldMinGrams(yieldImportDTO.minGrams());
                    existing.setYieldMaxGrams(yieldImportDTO.maxGrams());
                }, () -> {
                    // CREATE
                    VarietyProfile profile = new VarietyProfile();
                    profile.setVariety(variety);
                    profile.setKcalPerGram(nutritionImportDTO.kcalPerGram());
                    profile.setYieldMinGrams(yieldImportDTO.minGrams());
                    profile.setYieldMaxGrams(yieldImportDTO.maxGrams());

                    varietyProfileRepository.save(profile);
                });
    }


}
