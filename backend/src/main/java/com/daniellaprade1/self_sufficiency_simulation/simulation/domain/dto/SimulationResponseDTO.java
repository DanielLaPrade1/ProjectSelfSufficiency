package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto;

public record SimulationResponseDTO(
        double caloriesProduced,
        float selfSufficiencyPercentage
) {}
