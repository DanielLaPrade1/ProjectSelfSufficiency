package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto.YieldDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.VarietyProfile;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.mapper.NutritionMapper;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.mapper.YieldMapper;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.VarietyProfileRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.service.VarietyProfileService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VarietyProfileServiceImpl implements VarietyProfileService {

    private final VarietyProfileRepository varietyProfileRepository;
    private final NutritionMapper nutritionMapper;
    private final YieldMapper yieldMapper;

    public VarietyProfileServiceImpl(VarietyProfileRepository varietyProfileRepository, NutritionMapper nutritionMapper, YieldMapper yieldMapper) {
        this.varietyProfileRepository = varietyProfileRepository;
        this.nutritionMapper = nutritionMapper;
        this.yieldMapper = yieldMapper;
    }

    @Override
    @Transactional
    public void createOrUpdateVarietyProfile(
            Variety variety,
            NutritionDTO nutrition,
            YieldDTO yield
    ) {
        varietyProfileRepository.findByVarietyId(variety.getId())
                .ifPresentOrElse(existing -> {
                    // UPDATE
                    existing.setNutrition(nutritionMapper.toEmbeddable(nutrition));
                    existing.setYield(yieldMapper.toEmbeddable(yield));

                    varietyProfileRepository.save(existing);
                }, () -> {
                    // CREATE
                    VarietyProfile profile = new VarietyProfile();

                    profile.setVariety(variety);
                    profile.setNutrition(nutritionMapper.toEmbeddable(nutrition));
                    profile.setYield(yieldMapper.toEmbeddable(yield));

                    varietyProfileRepository.save(profile);
                });
    }
}
