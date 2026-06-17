package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject;

public record ValueRange(
        Double min,
        Double max
) {
    public double spread() {return max - min;}
    public double midpoint() {return (max + min) / 2;}

    public ValueRange add(ValueRange other) {
        return new ValueRange(
                this.min + other.min,
                this.max + other.max
        );
    }
}
