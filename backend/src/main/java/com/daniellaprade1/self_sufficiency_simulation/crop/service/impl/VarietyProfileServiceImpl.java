package com.daniellaprade1.self_sufficiency_simulation.crop.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.NutritionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.YieldDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.VarietyProfile;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.mapper.NutritionMapper;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.mapper.YieldMapper;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyProfileRepository;
import com.daniellaprade1.self_sufficiency_simulation.crop.service.VarietyProfileService;
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
