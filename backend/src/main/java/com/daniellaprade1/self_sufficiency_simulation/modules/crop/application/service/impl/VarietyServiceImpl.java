package com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.imp.VarietyImportDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Crop;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.VarietyService;
import org.springframework.stereotype.Service;

@Service
public class VarietyServiceImpl implements VarietyService {

    private final VarietyRepository varietyRepository;

    public VarietyServiceImpl(VarietyRepository varietyRepository) {
        this.varietyRepository = varietyRepository;
    }

    @Override
    public Variety createOrUpdateVariety(Crop crop, VarietyImportDTO varietyImportDTO) {
        return varietyRepository.findByNameAndCropId(varietyImportDTO.name(), crop.getId())
                .orElseGet(() -> {
                    // CREATE
                    Variety variety = new Variety();

                    variety.setCrop(crop);
                    variety.setName(varietyImportDTO.name());

                    return varietyRepository.save(variety);
                });
    }
}
