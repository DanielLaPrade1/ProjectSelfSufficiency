package com.daniellaprade1.self_sufficiency_simulation.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "nutrition")
public class Nutrition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float kcalPerGram;

    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

}
