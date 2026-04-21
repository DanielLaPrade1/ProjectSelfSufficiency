package com.daniellaprade1.self_sufficiency_simulation.crop.entity;

import jakarta.persistence.*;

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
