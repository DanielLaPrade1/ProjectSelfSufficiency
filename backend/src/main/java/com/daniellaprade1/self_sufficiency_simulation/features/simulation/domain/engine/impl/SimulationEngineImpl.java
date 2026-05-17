package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.NutritionTotalsDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;


@Component
public class SimulationEngineImpl implements SimulationEngine {

    private static final int DAYS_PER_YEAR = 365;

    @Override
    public SimulationResponseDTO run(List<SimulationCropData> cropData, Double dailyCalorieTarget) {

        Map<NutritionMetric, Double> nutritionTotals = new EnumMap<>(NutritionMetric.class);
        for (NutritionMetric metric : NutritionMetric.values()) {
            nutritionTotals.put(metric, 0.0);
        }

        // Calculate all nutrition metrics for each crop
        for (SimulationCropData crop : cropData) {
            for (NutritionMetric metric : NutritionMetric.values()) {
                double totalValue = computeTotalNutritionMetricValuePerCrop(
                        crop,
                        metric.extract(crop)
                );
                nutritionTotals.merge(metric, totalValue, Double::sum);
            }
        }

        // Calories
        double caloriesProduced = nutritionTotals.get(NutritionMetric.CALORIES);
        double yearlyCalorieTarget = dailyCalorieTarget * DAYS_PER_YEAR;
        double selfSufficiencyPercentage = caloriesProduced / yearlyCalorieTarget;

        return new SimulationResponseDTO(new NutritionTotalsDTO(nutritionTotals), selfSufficiencyPercentage);
    }

    private double computeTotalNutritionMetricValuePerCrop(SimulationCropData crop, Double value) {
        double avgYield = (crop.yieldMin() + crop.yieldMax()) / 2;
        double valuePerUnit = avgYield * value;
        return valuePerUnit * crop.units();
    }
}
