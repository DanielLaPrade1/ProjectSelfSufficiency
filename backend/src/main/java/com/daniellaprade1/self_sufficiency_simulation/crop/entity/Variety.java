package com.daniellaprade1.self_sufficiency_simulation.crop.entity;

import jakarta.persistence.*;

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
    private Profile profile;
}
