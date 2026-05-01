package com.daniellaprade1.self_sufficiency_simulation.crop.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.CropRepository;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.crop.service.CropService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final VarietyRepository varietyRepository;

    public CropServiceImpl(CropRepository cropRepository, VarietyRepository varietyRepository) {
        this.cropRepository = cropRepository;
        this.varietyRepository = varietyRepository;
    }

    // Unimplemented
    @Override
    public List<CropOptionDTO> getAllCropOptions() {
        return null;
    }

    @Override
    public Crop findOrCreateCrop(String name, String species, List<Variety> varieties) {
        return cropRepository.findByName(name)
                .orElseGet(() -> {
                    return new Crop(name, species, varieties);
                });
    }


}
