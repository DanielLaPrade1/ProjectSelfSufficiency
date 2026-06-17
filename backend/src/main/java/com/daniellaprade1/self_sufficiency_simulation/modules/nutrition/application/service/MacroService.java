package com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.dto.MacroPresetResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.dto.MacroDistributionRequestDTO;

import java.util.List;

public interface MacroService {
    List<MacroPresetResponseDTO> getAllPresets();
    MacroDistribution resolveMacroDistribution(MacroDistributionRequestDTO MacroDistributionRequestDTO);
}
