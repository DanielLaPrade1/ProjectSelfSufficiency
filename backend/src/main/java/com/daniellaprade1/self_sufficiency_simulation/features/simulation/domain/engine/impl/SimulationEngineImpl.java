package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.ValueRange;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.NutritionResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.CropInput;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result.SimulationResult;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;


@Component
public class SimulationEngineImpl implements SimulationEngine {

    private static final int SIMULATION_LENGTH_DAYS = 365;

    @Override
    public SimulationResult run(SimulationParameters parameters) {

        ValueRange baseNutritionRange = new ValueRange(0.0, 0.0);

        Map<NutritionMetric, ValueRange> nutritionTotals = new EnumMap<>(NutritionMetric.class);
        for (NutritionMetric metric : NutritionMetric.values()) {
            nutritionTotals.put(metric, baseNutritionRange);
        }

        // Calculate all nutrition metrics for each crop
        for (CropInput crop : parameters.cropInputs()) {
            for (NutritionMetric metric : NutritionMetric.values()) {
                ValueRange metricYieldRange = computeTotalNutritionRange(
                        crop,
                        metric.extract(crop.nutrition())
                );
                nutritionTotals.merge(metric, metricYieldRange, ValueRange::add);
            }
        }

        // Calories are the only determinant of selfSufficiencyPercentage right now
        ValueRange caloriesProduced = nutritionTotals.get(NutritionMetric.CALORIES);
        double simulationCalorieTarget = parameters.dailyCalorieTarget() * SIMULATION_LENGTH_DAYS;
        double selfSufficiencyPercentage = caloriesProduced.midpoint() / simulationCalorieTarget;

        // Calculate all nutrition metric targets
        Map<NutritionMetric, Double> nutritionTargets = new EnumMap<>(NutritionMetric.class);

        nutritionTargets.put(NutritionMetric.CALORIES, simulationCalorieTarget);

        for (NutritionMetric metric : NutritionMetric.macros()) {
            double macroSplit = parameters.macroDistributionInput().getFromMetric(metric) / 100;
            double simulationMacrosNeeded = macroSplit * simulationCalorieTarget;
            nutritionTargets.put(metric, simulationMacrosNeeded);
        }

        return new SimulationResult(
                new NutritionResponseDTO(nutritionTotals, nutritionTargets),
                selfSufficiencyPercentage
        );
    }

    private ValueRange computeTotalNutritionRange(CropInput crop, Double nutritionalValue) {
        Yield cropYield = crop.yieldRange();

        double minYieldTotal = cropYield.getMinGrams() * crop.units();
        double maxYieldTotal = cropYield.getMaxGrams() * crop.units();

        return new ValueRange(
                minYieldTotal * nutritionalValue,
                maxYieldTotal * nutritionalValue
        );
    }
}
