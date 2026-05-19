package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine;

import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.MacroDistributionPreset;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl.SimulationEngineImpl;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationEngineTest {

    private final SimulationEngine simulationEngine;

    public SimulationEngineTest() {
        this.simulationEngine = new SimulationEngineImpl();
    }

    @Test
    void shouldCalculateCaloriesCorrectly() {
        // Arrange
        double cropUnits = 100;
        Nutrition cropNutrition = new Nutrition(0.1, 0d, 0d, 0d);
        Yield cropYield = new Yield(10d, 30d);

        List<CropData> crops = List.of(
                new CropData(cropUnits, cropNutrition, cropYield)
        );

        MacroDistribution ketoDistribution = MacroDistributionPreset.KETO.getDistribution();

        // Act
        SimulationResponseDTO result = simulationEngine.run(crops, ketoDistribution, 200.0);

        // Assert
        // avg yield = (10 + 30) / 2 = 20
        // calories per unit = 0.1 * 20 = 2
        // total calories = 100 * 2 = 200
        assertEquals(200, result.nutritionTotals().getTotal(NutritionMetric.CALORIES));
    }
}
