package com.daniellaprade1.self_sufficiency_simulation.crop.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class VarietyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float kcalPerGram;
    private int yieldMinGrams;
    private int yieldMaxGrams;

    @OneToOne
    @JoinColumn(name = "variety")
    private Variety variety;

    public VarietyProfile() {
    }

    public VarietyProfile(Long id, float kcalPerGram, int yieldMinGrams, int yieldMaxGrams, Variety variety) {
        this.id = id;
        this.kcalPerGram = kcalPerGram;
        this.yieldMinGrams = yieldMinGrams;
        this.yieldMaxGrams = yieldMaxGrams;
        this.variety = variety;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getKcalPerGram() {
        return kcalPerGram;
    }

    public void setKcalPerGram(float kcalPerGram) {
        this.kcalPerGram = kcalPerGram;
    }

    public int getYieldMinGrams() {
        return yieldMinGrams;
    }

    public void setYieldMinGrams(int yieldMinGrams) {
        this.yieldMinGrams = yieldMinGrams;
    }

    public int getYieldMaxGrams() {
        return yieldMaxGrams;
    }

    public void setYieldMaxGrams(int yieldMaxGrams) {
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
