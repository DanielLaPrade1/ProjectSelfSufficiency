package com.daniellaprade1.self_sufficiency_simulation.modules.crop.web.controller;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.dto.CropOptionDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.application.service.CropService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CropOptionDTO>> getAll() {
        List<CropOptionDTO> response = cropService.getAllCropOptions();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
