package com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public class Yield {
    private Double minGrams;
    private Double maxGrams;

    public Yield() {}

    public Yield(Double minGrams, Double maxGrams) {
        this.minGrams = minGrams;
        this.maxGrams = maxGrams;
    }

    public Double getMinGrams() {return this.minGrams;}

    public void setMinGrams(Double minGrams) {this.minGrams = minGrams;}

    public Double getMaxGrams() {return this.maxGrams;}

    public void setMaxGrams(Double maxGrams) {this.maxGrams = maxGrams;}
}
