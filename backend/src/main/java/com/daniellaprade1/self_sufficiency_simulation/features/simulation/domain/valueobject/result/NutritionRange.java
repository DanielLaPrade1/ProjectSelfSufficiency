package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result;

public record NutritionRange(
        Double yieldMin,
        Double yieldMax
) {
    public double spread() {return yieldMax - yieldMin;}
    public double midpoint() {return (yieldMax + yieldMin) / 2;}

    public NutritionRange add(NutritionRange other) {
        return new NutritionRange(
                this.yieldMin + other.yieldMin,
                this.yieldMax + other.yieldMax
        );
    }
}
