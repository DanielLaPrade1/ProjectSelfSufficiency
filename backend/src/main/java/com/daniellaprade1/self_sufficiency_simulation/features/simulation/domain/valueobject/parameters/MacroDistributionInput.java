package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters;

public record MacroDistributionInput(
        double proteinPercent,
        double fatPercent,
        double carbsPercent
) {
    public MacroDistributionInput {
        double total = proteinPercent + fatPercent + carbsPercent;
        if (Math.abs(total - 100.0) > 0.01) {
            throw new IllegalArgumentException(
                    "Macro ratio must total 100%, got: " + total
            );
        }
    }

    public double getByKey(String key) {
        return switch (key) {
            case "PROTEIN" -> proteinPercent;
            case "FAT" -> fatPercent;
            case "CARBS" -> carbsPercent;
            default -> throw new UnsupportedOperationException(
                    "No macro ratio mapping for key: " + key
            );
        };
    }
}
