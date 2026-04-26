package com.daniellaprade1.self_sufficiency_simulation.crop.service;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;

import java.util.List;

public interface CropService {
    public List<CropOptionDTO> getAllCropOptions();
}
