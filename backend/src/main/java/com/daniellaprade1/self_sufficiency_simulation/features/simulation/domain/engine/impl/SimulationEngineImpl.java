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

import java.util.List;
import java.util.Map;


@Component
public class SimulationEngineImpl implements SimulationEngine {

    private static final int SIMULATION_LENGTH_DAYS = 365;

    @Override
    public SimulationResult run(SimulationParameters parameters) {

        // Extract metrics
        List<MetricValue> nutritionMetrics = parameters.cropInputs().getFirst().metrics();

        // Compute nutrition metric totals and build results
        Map<String, NutritionMetricResult> nutritionMetricResults = new java.util.HashMap<>();

        for (MetricValue metricValue : nutritionMetrics) {
            Double metricNutritionTarget = computeTargetNutritionValue(
                    metricValue,
                    parameters.macroDistributionInput(),
                    parameters.dailyCalorieTarget()
            );

            // Initialize totals to 0 with computed targets
            NutritionMetricResult currMetricResults =
                    new NutritionMetricResult(
                            new ValueRange(0d, 0d),
                            metricNutritionTarget
                    );

            nutritionMetricResults.put(metricValue.key(), currMetricResults);
        }

        // Compute nutrition targets and add to results
        for (CropInput cropInput : parameters.cropInputs()) {
            for (MetricValue metricValue : cropInput.metrics()) {
                NutritionMetricResult metricResult = nutritionMetricResults.get(metricValue.key());

                ValueRange currMetricTotals = computeTotalNutritionRange(cropInput, metricValue.value());
                ValueRange newMetricTotals = metricResult.totals().add(currMetricTotals);

                nutritionMetricResults.put(
                        metricValue.key(),
                        new NutritionMetricResult(newMetricTotals, metricResult.target())
                );
            }
        }

        // Compute self-sufficiency percentage (only calorie related for now)
        double totalCalories = nutritionMetricResults.get("CALORIES").totals().midpoint();
        double targetCalories = nutritionMetricResults.get("CALORIES").target();
        Double selfSufficiencyPercentage = totalCalories / targetCalories;

        return new SimulationResult(nutritionMetricResults, selfSufficiencyPercentage);

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

    private Double computeTargetNutritionValue(
            MetricValue metricValue,
            MacroDistributionInput macroDistribution,
            Double dailyCalorieTarget
    ) {
        double simulationCalorieTarget = dailyCalorieTarget * SIMULATION_LENGTH_DAYS;
        if (metricValue.key().equals("CALORIES")) {
            return simulationCalorieTarget;
        }
        double macroSplitDecimal = macroDistribution.getByKey(metricValue.key()) / 100;
        return simulationCalorieTarget * macroSplitDecimal;
    }

}
