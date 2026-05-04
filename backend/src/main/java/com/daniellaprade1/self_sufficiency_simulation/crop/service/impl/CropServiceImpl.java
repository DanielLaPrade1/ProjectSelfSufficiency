package com.daniellaprade1.self_sufficiency_simulation.crop.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto.imp.CropImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.mapper.CropMapper;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.CropRepository;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.crop.service.CropService;
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
