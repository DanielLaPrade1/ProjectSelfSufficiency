package com.daniellaprade1.self_sufficiency_simulation.crop.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.VarietyImportDTO;
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

    public CropServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    // Unimplemented
    @Override
    public List<CropOptionDTO> getAllCropOptions() {
        return null;
    }

    @Override
    public Crop createOrUpdateCrop(CropImportDTO cropImportDTO) {
        return cropRepository.findByName(cropImportDTO.name())
                .map(existing -> {
                    // UPDATE
                    existing.setSpecies(cropImportDTO.species());
                    return existing;
                })
                .orElseGet(() -> {
                    // CREATE
                    Crop crop = new Crop();
                    crop.setName(cropImportDTO.name());
                    crop.setSpecies(cropImportDTO.species());
                    return cropRepository.save(crop);
                });
    }
}
