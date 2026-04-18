package com.daniellaprade1.self_sufficiency_simulation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "yield_data")
public class Yield {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int yieldMinGrams;
    private int yieldMaxGrams;

    @OneToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

}
