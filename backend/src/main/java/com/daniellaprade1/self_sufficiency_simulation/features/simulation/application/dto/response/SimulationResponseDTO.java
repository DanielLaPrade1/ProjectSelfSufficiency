package com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response;

public record SimulationResponseDTO(
        NutritionTotalsDTO nutritionTotals,
        Double selfSufficiencyPercentage
) {}
