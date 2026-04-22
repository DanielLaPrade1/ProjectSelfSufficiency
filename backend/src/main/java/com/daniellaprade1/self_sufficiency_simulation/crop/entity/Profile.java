package com.daniellaprade1.self_sufficiency_simulation.crop.entity;

import jakarta.persistence.*;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float kcalPerGram;
    private int yieldMinGrams;
    private int yieldMaxGrams;

    @OneToOne
    @JoinColumn(name = "variety")
    private Variety variety;
}
