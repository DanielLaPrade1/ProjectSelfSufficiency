package com.daniellaprade1.self_sufficiency_simulation.simulation.engine;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.engine.impl.SimulationEngineImpl;
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
        List<SimulationCropData> crops = List.of(
                new SimulationCropData(100.0, 0.1, 10d, 30d)
        );

        SimulationResponseDTO result = simulationEngine.run(crops, 200.0);

        // avg yield = (10 + 30) / 2 = 20
        // calories per unit = 0.1 * 20 = 2
        // total calories = 100 * 2 = 2000
        assertEquals(200, result.caloriesProduced());
    }
}
