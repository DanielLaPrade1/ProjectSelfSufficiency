package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.enums;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.MacroDistribution;

public enum MacroDistributionPreset {
    STANDARD(55, 25, 20),
    MUSCLE_BUILDING(50, 35, 15),
    KETO(10, 30, 60);

    private final MacroDistribution distribution;

    MacroDistributionPreset(double protein, double fat, double carbs) {
        this.distribution = new MacroDistribution(protein, fat, carbs)
;    }

    public MacroDistribution getDistribution() {
        return distribution;
    }
}
