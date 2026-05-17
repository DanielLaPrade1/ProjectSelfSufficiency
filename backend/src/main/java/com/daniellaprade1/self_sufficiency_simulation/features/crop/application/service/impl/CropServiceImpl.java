package com.daniellaprade1.self_sufficiency_simulation.features.crop.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.mapper.CropMapper;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.CropRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.service.CropService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final VarietyRepository varietyRepository;
    private final CropMapper cropMapper;

    public CropServiceImpl(CropRepository cropRepository, VarietyRepository varietyRepository, CropMapper cropMapper) {
        this.cropRepository = cropRepository;
        this.varietyRepository = varietyRepository;
        this.cropMapper = cropMapper;
    }

    @Override
    public List<CropOptionDTO> getAllCropOptions() {
        return varietyRepository.findAll()
                .stream()
                .map(cropMapper::toOptionDTO).toList();
    }

    @Override
    public Crop createOrUpdateCrop(CropImportDTO cropImportDTO) {
        return cropRepository.findByName(cropImportDTO.name())
                .map(existing -> {
                    // UPDATE
                    existing.setSpecies(cropImportDTO.species());

                    return cropRepository.save(existing);
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
