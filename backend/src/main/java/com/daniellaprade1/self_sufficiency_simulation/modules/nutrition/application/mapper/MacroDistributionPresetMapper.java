package com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.mapper;

import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.dto.MacroPresetResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.enums.MacroDistributionPreset;
import org.springframework.stereotype.Component;

@Component
public class MacroDistributionPresetMapper {

    public MacroPresetResponseDTO toMacroPresetResponseDTO(MacroDistributionPreset preset) {
        return new MacroPresetResponseDTO(
                preset.name(),
                preset.getDistribution().getProteinPercent(),
                preset.getDistribution().getFatPercent(),
                preset.getDistribution().getCarbsPercent()
        );
    }

}
