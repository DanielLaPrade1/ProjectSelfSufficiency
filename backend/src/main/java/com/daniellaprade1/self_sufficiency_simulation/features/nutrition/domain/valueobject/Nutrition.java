package com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject;

import jakarta.persistence.Embeddable;

@Embeddable
public class Nutrition {

    private Double kcalPerGram;
    private Double proteinPerGram;
    private Double totalFatPerGram;
    private Double totalCarbsPerGram;

    public Nutrition() {}

    public Nutrition(Double kcalPerGram, Double proteinPerGram, Double totalFatPerGram, Double totalCarbsPerGram) {
        this.kcalPerGram = kcalPerGram;
        this.proteinPerGram = proteinPerGram;
        this.totalFatPerGram = totalFatPerGram;
        this.totalCarbsPerGram = totalCarbsPerGram;
    }

    public Double getKcalPerGram() {return kcalPerGram;}

    public void setKcalPerGram(Double kcalPerGram) {
        this.kcalPerGram = kcalPerGram;
    }

    public Double getProteinPerGram() {
        return proteinPerGram;
    }

    public void setProteinPerGram(Double proteinPerGram) {
        this.proteinPerGram = proteinPerGram;
    }

    public Double getTotalFatPerGram() {
        return totalFatPerGram;
    }

    public void setTotalFatPerGram(Double totalFatPerGram) {
        this.totalFatPerGram = totalFatPerGram;
    }

    public Double getTotalCarbsPerGram() {
        return totalCarbsPerGram;
    }

    public void setTotalCarbsPerGram(Double totalCarbsPerGram) {
        this.totalCarbsPerGram = totalCarbsPerGram;
    }
}