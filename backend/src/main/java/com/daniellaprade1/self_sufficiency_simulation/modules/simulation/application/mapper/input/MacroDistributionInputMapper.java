package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input;

import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.MacroDistributionInput;
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
