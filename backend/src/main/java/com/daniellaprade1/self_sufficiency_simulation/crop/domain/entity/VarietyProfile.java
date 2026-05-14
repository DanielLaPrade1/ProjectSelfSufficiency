package com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class VarietyProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Nutrition nutrition;
    private Yield yield;

    @OneToOne
    @JoinColumn(name = "variety_id")
    private Variety variety;

    public VarietyProfile() {}

    public VarietyProfile(UUID id, Nutrition nutrition, Yield yield, Variety variety) {
        this.id = id;
        this.nutrition = nutrition;
        this.yield = yield;
        this.variety = variety;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(Nutrition nutrition) {this.nutrition = nutrition;}

    public Yield getYield() {return yield;}

    public void setYield(Yield yield) {this.yield = yield;}

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
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VarietyProfile{" +
                "id=" + id +
                ", nutrition=" + nutrition +
                ", yield=" + yield +
                ", variety=" + variety +
                '}';
    }
}
