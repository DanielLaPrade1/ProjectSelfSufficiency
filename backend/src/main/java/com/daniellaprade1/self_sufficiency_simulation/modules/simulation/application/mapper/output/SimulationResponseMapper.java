package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.output;

import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response.NutritionResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.ValueRange;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result.NutritionMetricResult;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result.SimulationResult;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SimulationResponseMapper {
    public SimulationResponseDTO toNutritionResponseDTO(SimulationResult result) {

        // SimulationResult metric results -> NutritionResponse
        Map<NutritionMetric, ValueRange> totals = new java.util.HashMap<>(Map.of());
        Map<NutritionMetric, Double> targets = new java.util.HashMap<>(Map.of());

        Map<String, NutritionMetricResult> metricResults = result.nutritionMetricResults();
        for (String metricName : metricResults.keySet()) {
            NutritionMetric metric = NutritionMetric.valueOf(metricName);

            ValueRange metricTotals = metricResults.get(metricName).totals();
            totals.put(metric, metricTotals);

            double metricTarget = metricResults.get(metricName).target();
            targets.put(metric, metricTarget);
        }

        NutritionResponseDTO nutritionTotals = new NutritionResponseDTO(totals, targets);

        return new SimulationResponseDTO(nutritionTotals, result.selfSufficiencyPercentage());
    }
}
