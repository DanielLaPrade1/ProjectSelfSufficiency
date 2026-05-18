package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.service.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroPresetResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.mapper.MacroDistributionPresetMapper;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.service.MacroService;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.MacroDistributionPreset;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroDistributionRequestDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MacroServiceImpl implements MacroService {

    private final MacroDistributionPresetMapper mapper;

    public MacroServiceImpl(MacroDistributionPresetMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<MacroPresetResponseDTO> getAllPresets() {
        return Arrays.stream(MacroDistributionPreset.values())
                .map(mapper::toMacroPresetResponseDTO)
                .toList();
    }

    @Override
    public MacroDistribution resolveMacroDistribution(
            MacroDistributionRequestDTO macroDistributionRequest
    ) {
        if (macroDistributionRequest.isPresetMode()) return macroDistributionRequest.preset().getDistribution();
        else {
            return new MacroDistribution(
                    macroDistributionRequest.customDistribution().name(),
                    macroDistributionRequest.customDistribution().proteinPct(),
                    macroDistributionRequest.customDistribution().fatPct(),
                    macroDistributionRequest.customDistribution().carbsPct()
            );
        }
    }
}
