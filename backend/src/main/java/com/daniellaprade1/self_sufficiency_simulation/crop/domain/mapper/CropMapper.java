package com.daniellaprade1.self_sufficiency_simulation.crop.domain.mapper;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;

public interface CropMapper {
    public CropOptionDTO toOptionDTO(Variety variety);
}
