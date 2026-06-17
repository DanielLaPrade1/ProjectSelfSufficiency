package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.mapper;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.VarietyProfile;
import org.springframework.stereotype.Component;

@Component
public class CropMapper {

    public CropOptionDTO toOptionDTO(Variety variety) {
        Crop crop = variety.getCrop();
        VarietyProfile varietyProfile = variety.getProfile();

        String varietyImageUrl = "/images/crop"
                + "/" + crop.getName().toLowerCase()
                + "/" + variety.getName().toLowerCase()
                + "-logo.svg";

        return new CropOptionDTO(
                variety.getId(),
                crop.getName(),
                crop.getSpecies(),
                variety.getName(),
                varietyImageUrl,
                varietyProfile.getNutrition(),
                varietyProfile.getYield()
        );
    }
}
