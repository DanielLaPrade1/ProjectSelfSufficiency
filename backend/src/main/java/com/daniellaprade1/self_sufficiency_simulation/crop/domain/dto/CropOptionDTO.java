package com.daniellaprade1.self_sufficiency_simulation.crop.domain.dto;


import java.util.UUID;
import jakarta.validation.constraints.*;

public record CropOptionDTO (
    UUID varietyID,
    String cropName,
    String species,
    String varietyName,
    double kcalPerGram,
    double yieldMinGrams,
    double yieldMaxGrams
) {}
