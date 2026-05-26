package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.service;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroPresetResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroDistributionRequestDTO;

import java.util.List;

public interface MacroService {
    List<MacroPresetResponseDTO> getAllPresets();
    MacroDistribution resolveMacroDistribution(MacroDistributionRequestDTO MacroDistributionRequestDTO);
}
