package com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String species;

    @OneToMany(mappedBy = "crop", cascade = CascadeType.ALL)
    private List<Variety> varieties = new ArrayList<>();

    public Crop() {
    }

    public Crop(UUID id, String name, String species, List<Variety> varieties) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.varieties = varieties;
    }

    public Crop(String name, String species, List<Variety> varieties) {
        this.name = name;
        this.species = species;
        this.varieties = varieties;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public List<Variety> getVarieties() {
        return varieties;
    }

    public void setVarieties(List<Variety> varieties) {
        this.varieties = varieties;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return Objects.equals(id, crop.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", varieties=" + varieties +
                '}';
    }
}
