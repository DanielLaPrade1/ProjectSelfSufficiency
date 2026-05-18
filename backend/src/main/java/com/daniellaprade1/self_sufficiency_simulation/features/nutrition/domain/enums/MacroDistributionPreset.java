package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums;

import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;

public enum MacroDistributionPreset {
    STANDARD(30, 30, 40),
    MUSCLE_BUILDING(50, 35, 15),
    KETO(5, 25, 70);

    private final MacroDistribution distribution;

    MacroDistributionPreset(double protein, double fat, double carbs) {
        this.distribution = new MacroDistribution(protein, fat, carbs)
;    }

    public MacroDistribution getDistribution() {
        return distribution;
    }
}
