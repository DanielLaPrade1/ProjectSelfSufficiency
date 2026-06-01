package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.mapper;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MacroDistributionInput;
import org.springframework.stereotype.Component;

@Component
public class MacroDistributionInputMapper {
    public MacroDistributionInput toMacroDistributionInput(MacroDistribution distribution) {
        return new MacroDistributionInput(
                distribution.getProteinPercent(),
                distribution.getFatPercent(),
                distribution.getCarbsPercent()
        );
    }
}
