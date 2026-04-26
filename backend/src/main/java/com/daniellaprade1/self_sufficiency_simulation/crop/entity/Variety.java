package com.daniellaprade1.self_sufficiency_simulation.crop.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "variety")
public class Variety {

    @Id
    @GeneratedValue
    UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @OneToOne(mappedBy = "variety", cascade = CascadeType.ALL)
    private VarietyProfile profile;

    public Variety() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public VarietyProfile getProfile() {
        return profile;
    }

    public void setProfile(VarietyProfile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Variety variety = (Variety) o;
        return Objects.equals(id, variety.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Variety{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", crop=" + crop +
                ", profile=" + profile +
                '}';
    }
}
