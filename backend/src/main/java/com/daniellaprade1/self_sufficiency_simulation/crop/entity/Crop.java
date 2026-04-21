package com.daniellaprade1.self_sufficiency_simulation.crop.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String category;
    private String species;

    @OneToOne(mappedBy = "crop", cascade = CascadeType.ALL)
    private Nutrition nutrition;

    @OneToOne(mappedBy = "crop", cascade = CascadeType.ALL)
    private Yield yield;

}
