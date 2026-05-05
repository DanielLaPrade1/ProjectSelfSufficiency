package com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class VarietyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double kcalPerGram;
    private Double yieldMinGrams;
    private Double yieldMaxGrams;

    @OneToOne
    @JoinColumn(name = "variety_id")
    private Variety variety;

    public VarietyProfile() {
    }

    public VarietyProfile(UUID id, Double kcalPerGram, Double yieldMinGrams, Double yieldMaxGrams, Variety variety) {
        this.id = id;
        this.kcalPerGram = kcalPerGram;
        this.yieldMinGrams = yieldMinGrams;
        this.yieldMaxGrams = yieldMaxGrams;
        this.variety = variety;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getKcalPerGram() {
        return kcalPerGram;
    }

    public void setKcalPerGram(Double kcalPerGram) {
        this.kcalPerGram = kcalPerGram;
    }

    public Double getYieldMinGrams() {
        return yieldMinGrams;
    }

    public void setYieldMinGrams(Double yieldMinGrams) {
        this.yieldMinGrams = yieldMinGrams;
    }

    public Double getYieldMaxGrams() {
        return yieldMaxGrams;
    }

    public void setYieldMaxGrams(Double yieldMaxGrams) {
        this.yieldMaxGrams = yieldMaxGrams;
    }

    public Variety getVariety() {
        return variety;
    }

    public void setVariety(Variety variety) {
        this.variety = variety;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VarietyProfile profile = (VarietyProfile) o;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "VarietyProfile{" +
                "id=" + id +
                ", kcalPerGram=" + kcalPerGram +
                ", yieldMinGrams=" + yieldMinGrams +
                ", yieldMaxGrams=" + yieldMaxGrams +
                ", variety=" + variety +
                '}';
    }
}
