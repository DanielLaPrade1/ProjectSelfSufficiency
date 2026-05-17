package com.daniellaprade1.self_sufficiency_simulation.features.crop.web.controller;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.application.service.CropService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/crops")
public class CropController {

    private final CropService cropService;

    public CropController(CropService cropService) {
        this.cropService = cropService;
    }

    @GetMapping
    public List<CropOptionDTO> getAll() {
        return cropService.getAllCropOptions();
    }
}
