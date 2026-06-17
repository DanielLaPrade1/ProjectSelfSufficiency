package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response;

public record SimulationResponseDTO(
        NutritionResponseDTO nutritionTotals,
        Double selfSufficiencyPercentage
) {}
