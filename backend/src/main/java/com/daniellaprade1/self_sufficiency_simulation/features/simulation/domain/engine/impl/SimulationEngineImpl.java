package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.NutritionResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;


@Component
public class SimulationEngineImpl implements SimulationEngine {

    private static final int SIMULATION_LENGTH_DAYS = 365;

    @Override
    public SimulationResponseDTO run(List<CropData> cropData, MacroDistribution macroDistribution, Double dailyCalorieTarget) {

        Map<NutritionMetric, Double> nutritionTotals = new EnumMap<>(NutritionMetric.class);
        for (NutritionMetric metric : NutritionMetric.values()) {
            nutritionTotals.put(metric, 0.0);
        }

        // Calculate all nutrition metrics for each crop
        for (CropData crop : cropData) {
            for (NutritionMetric metric : NutritionMetric.values()) {
                double totalValue = computeTotalNutritionMetricValue(
                        crop,
                        metric.extract(crop.nutrition())
                );
                nutritionTotals.merge(metric, totalValue, Double::sum);
            }
        }

        // Nutrition Needed
        Map<NutritionMetric, Double> nutritionTargets = new EnumMap<>(NutritionMetric.class);

        // Calories are the only determinant of selfSufficiencyPercentage right now
        double caloriesProduced = nutritionTotals.get(NutritionMetric.CALORIES);
        double simulationCalorieTarget = dailyCalorieTarget * SIMULATION_LENGTH_DAYS;
        double selfSufficiencyPercentage = caloriesProduced / simulationCalorieTarget;

        nutritionTargets.put(NutritionMetric.CALORIES, simulationCalorieTarget);

        // Macros
        for (NutritionMetric metric : NutritionMetric.values()) {
            if (metric.isMacro()) {
                double simulationMacrosNeeded = macroDistribution.getFromMetric(metric) * simulationCalorieTarget;
                nutritionTargets.put(metric, simulationMacrosNeeded);
            }
        }

        return new SimulationResponseDTO(
                new NutritionResponseDTO(nutritionTotals, nutritionTargets),
                selfSufficiencyPercentage
        );
    }

    private double computeTotalNutritionMetricValue(CropData crop, Double value) {
        Yield cropYield = crop.yield();

        double avgYield = (cropYield.getMinGrams() + cropYield.getMaxGrams()) / 2;
        double valuePerUnit = avgYield * value;
        return valuePerUnit * crop.units();
    }
}
