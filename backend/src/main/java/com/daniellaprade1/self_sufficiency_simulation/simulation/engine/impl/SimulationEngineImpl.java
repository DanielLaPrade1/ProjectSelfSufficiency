package com.daniellaprade1.self_sufficiency_simulation.simulation.engine.impl;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.engine.SimulationEngine;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SimulationEngineImpl implements SimulationEngine {

    private static final int DAYS_PER_YEAR = 365;

    @Override
    public SimulationResponseDTO run(List<SimulationCropData> cropData, Double dailyCalorieTarget) {

        double caloriesProduced = 0.0;

        for (SimulationCropData crop: cropData) {
            double avgYield = (crop.yieldMin() + crop.yieldMax()) / 2;
            double caloriesPerUnit = avgYield * crop.kcalPerGram();
            double totalCalories = caloriesPerUnit * crop.units();

            caloriesProduced += totalCalories;
        }

        double yearlyTarget = dailyCalorieTarget * DAYS_PER_YEAR;
        double selfSufficiencyPercentage = caloriesProduced / yearlyTarget;

        return new SimulationResponseDTO(caloriesProduced, selfSufficiencyPercentage);
    }
}
