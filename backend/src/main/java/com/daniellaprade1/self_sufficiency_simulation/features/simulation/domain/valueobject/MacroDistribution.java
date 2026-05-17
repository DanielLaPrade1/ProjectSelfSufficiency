package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public class MacroDistribution {

    private Double proteinPercent;
    private Double fatPercent;
    private Double carbsPercent;

    public MacroDistribution() {}

    public MacroDistribution(
            Double proteinPercent,
            Double fatPercent,
            Double carbsPercent
    ) {
        validate(carbsPercent, proteinPercent, fatPercent);

        this.carbsPercent = carbsPercent;
        this.proteinPercent = proteinPercent;
        this.fatPercent = fatPercent;
    }

    public Double getProteinPercent() {
        return proteinPercent;
    }

    public void setProteinPercent(Double proteinPercent) {
        this.proteinPercent = proteinPercent;
    }

    public Double getFatPercent() {
        return fatPercent;
    }

    public void setFatPercent(Double fatPercent) {
        this.fatPercent = fatPercent;
    }

    public Double getCarbsPercent() {
        return carbsPercent;
    }

    public void setCarbsPercent(Double carbsPercent) {
        this.carbsPercent = carbsPercent;
    }

    private void validate(
            Double carbs,
            Double protein,
            Double fat
    ) {
        double total = carbs + protein + fat;

        if (Math.abs(total - 100.0) > 0.01) {
            throw new IllegalArgumentException(
                    "Macro distribution must total 100%"
            );
        }
    }



}
