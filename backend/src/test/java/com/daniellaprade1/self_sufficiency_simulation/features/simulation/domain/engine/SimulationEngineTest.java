package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine;

import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl.SimulationEngineImpl;
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
        List<SimulationCropData> crops = List.of(
                new SimulationCropData(100.0, 0.1, 0d, 0d, 0d, 10d, 30d)
        );

        // Act
        SimulationResponseDTO result = simulationEngine.run(crops, 200.0);

        // Assert
        // avg yield = (10 + 30) / 2 = 20
        // calories per unit = 0.1 * 20 = 2
        // total calories = 100 * 2 = 200
        assertEquals(200, result.nutritionTotals().get(NutritionMetric.CALORIES));
    }
}
