package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.result.NutritionRange;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.NutritionResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.parameters.CropData;
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
    public SimulationResponseDTO run(SimulationParameters parameters) {

        NutritionRange baseNutritionRange = new NutritionRange(0.0, 0.0);

        Map<NutritionMetric, NutritionRange> nutritionTotals = new EnumMap<>(NutritionMetric.class);
        for (NutritionMetric metric : NutritionMetric.values()) {
            nutritionTotals.put(metric, baseNutritionRange);
        }

        // Calculate all nutrition metrics for each crop
        for (CropData crop : parameters.cropData()) {
            for (NutritionMetric metric : NutritionMetric.values()) {
                NutritionRange metricYieldRange = computeTotalNutritionRange(
                        crop,
                        metric.extract(crop.nutrition())
                );
                nutritionTotals.merge(metric, metricYieldRange, NutritionRange::add);
            }
        }

        // Calories are the only determinant of selfSufficiencyPercentage right now
        NutritionRange caloriesProduced = nutritionTotals.get(NutritionMetric.CALORIES);
        double simulationCalorieTarget = parameters.dailyCalorieTarget() * SIMULATION_LENGTH_DAYS;
        double selfSufficiencyPercentage = caloriesProduced.midpoint() / simulationCalorieTarget;

        // Calculate all nutrition metric targets
        Map<NutritionMetric, Double> nutritionTargets = new EnumMap<>(NutritionMetric.class);

        nutritionTargets.put(NutritionMetric.CALORIES, simulationCalorieTarget);

        for (NutritionMetric metric : NutritionMetric.macros()) {
            double macroSplit = parameters.macroDistribution().getFromMetric(metric) / 100;
            double simulationMacrosNeeded = macroSplit * simulationCalorieTarget;
            nutritionTargets.put(metric, simulationMacrosNeeded);
        }

        return new SimulationResponseDTO(
                new NutritionResponseDTO(nutritionTotals, nutritionTargets),
                selfSufficiencyPercentage
        );
    }

    private NutritionRange computeTotalNutritionRange(CropData crop, Double nutritionalValue) {
        Yield cropYield = crop.yield();

        double minYieldTotal = cropYield.getMinGrams() * crop.units();
        double maxYieldTotal = cropYield.getMaxGrams() * crop.units();

        return new NutritionRange(
                minYieldTotal * nutritionalValue,
                maxYieldTotal * nutritionalValue
        );
    }
}
