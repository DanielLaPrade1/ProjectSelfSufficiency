package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MacroDistributionInput;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MetricValue;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.ValueRange;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.CropInput;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result.NutritionMetricResult;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result.SimulationResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;


@Component
public class SimulationEngineImpl implements SimulationEngine {

    private static final int SIMULATION_LENGTH_DAYS = 365;

    @Override
    public SimulationResult run(SimulationParameters parameters) {

        // Calculate all nutrition metric totals / targets
        Map<String, NutritionMetricResult> nutritionMetricTotals = new java.util.HashMap<>();

        for (CropInput cropInput : parameters.cropInputs()) {
            for (MetricValue metricValue : cropInput.metrics()) {

                ValueRange metricNutritionTotalRange = computeTotalNutritionRange(cropInput, metricValue.value());
                Double metricNutritionTarget = computeTargetNutritionValue(
                        metricValue,
                        parameters.macroDistributionInput(),
                        parameters.dailyCalorieTarget()
                );

                nutritionMetricTotals.put(
                        metricValue.key(),
                        new NutritionMetricResult(metricNutritionTotalRange, metricNutritionTarget)
                );
            }
        }

        // Compute self-sufficiency percentage
        Double selfSufficiencyPercentage =
                nutritionMetricTotals.get("CALORIES").totals().midpoint()
                        / nutritionMetricTotals.get("CALORIES").target();
        return new SimulationResult(nutritionMetricTotals, selfSufficiencyPercentage);
    }


    private ValueRange computeTotalNutritionRange(CropInput crop, Double metricValue) {
        ValueRange cropYield = crop.yieldRange();

        double minYieldTotal = cropYield.min() * crop.units();
        double maxYieldTotal = cropYield.max() * crop.units();

        return new ValueRange(
                minYieldTotal * metricValue,
                maxYieldTotal * metricValue
        );
    }

    private Double computeTargetNutritionValue(MetricValue metricValue, MacroDistributionInput macroDistribution, Double dailyCalorieTarget) {
        double simulationCalorieTarget = dailyCalorieTarget * SIMULATION_LENGTH_DAYS;
        if (metricValue.key().equals("CALORIES")) {
            return simulationCalorieTarget;
        }
        double macroSplitDecimal = macroDistribution.getByKey(metricValue.key()) / 100;
        return dailyCalorieTarget * macroSplitDecimal;
    }

}
