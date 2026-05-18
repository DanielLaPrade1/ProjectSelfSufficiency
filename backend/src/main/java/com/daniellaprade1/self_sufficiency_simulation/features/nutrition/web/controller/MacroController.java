package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.web.controller;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroPresetResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.service.MacroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("nutrition/macros")
public class MacroController {

    private final MacroService macroService;

    public MacroController(MacroService macroService) {
        this.macroService = macroService;
    }

    @GetMapping("/presets")
    public ResponseEntity<List<MacroPresetResponseDTO>> getAllPresets() {
        List<MacroPresetResponseDTO> response = macroService.getAllPresets();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
