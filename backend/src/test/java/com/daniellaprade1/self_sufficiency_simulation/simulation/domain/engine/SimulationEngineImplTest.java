package com.daniellaprade1.self_sufficiency_simulation.simulation.domain.engine;

import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.engine.impl.SimulationEngineImpl;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.ValueRange;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.CropInput;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.MacroDistributionInput;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.MetricValue;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result.SimulationResult;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class SimulationEngineImplTest {

    private final SimulationEngineImpl engine = new SimulationEngineImpl();

    // Helpers
    private MetricValue metric(String key, double v) {
        return new MetricValue(key, v);
    }
    private CropInput cropInput(double yieldMin, double yieldMax, double units, List<MetricValue> metrics) {
        return new CropInput(units, metrics, new ValueRange(yieldMin, yieldMax));
    }
    // Compute MacroDistribution from protein for simplicity
    private MacroDistributionInput macros(double proteinPercent) {
        double remainder = 100 - proteinPercent;
        return new MacroDistributionInput(proteinPercent, remainder / 2, remainder / 2);
    }

    // TEST CROPS
    private List<CropInput> crops() {
        // Crop A: yield 800-1200 g/unit, 10 units, 0.8 cal/g, 0.02 g protein/g
        // Crop B  yield 500-900 g/unit,  20 units, 1.0 cal/g, 0.09 g protein/g
        return List.of(
                cropInput(800, 1200, 10, List.of(metric("CALORIES", 0.8), metric("PROTEIN", 0.02))),
                cropInput(500, 900, 20, List.of(metric("CALORIES", 1.0), metric("PROTEIN", 0.09)))
        );
    }

    @Test
    void accumulatesTotalsAcrossCrops() {
        SimulationParameters params = new SimulationParameters(crops(), macros(30), 2000d, 365.0);
        SimulationResult result = engine.run(params);

        // CALORIES totals:
        //   A: (800*10*0.8 .. 1200*10*0.8) = (6400, 9600)
        //   B: (500*20*1.0 .. 900*20*1.0)  = (10,000, 18,000)
        //   sum = (16,400, 27,600)
        var cal = result.nutritionMetricResults().get("CALORIES").totals();
        assertThat(cal.min()).isEqualTo(16400.0);
        assertThat(cal.max()).isEqualTo(27600.0);

        // PROTEIN totals:
        //   A: (8000*0.02 .. 12000*0.02) = (160, 240)
        //   B: (10,000*0.09 .. 18,000*0.09) = (900, 1620)
        //   sum = (1060, 1860)
        var pro = result.nutritionMetricResults().get("PROTEIN").totals();
        assertThat(pro.min()).isEqualTo(1060.0);
        assertThat(pro.max()).isEqualTo(1860.0);
    }

    @Test
    void computesCalorieAndMacroTargets() {
        SimulationParameters params = new SimulationParameters(crops(), macros(30), 2000d, 365.0);
        SimulationResult result = engine.run(params);

        // simCalTarget = 2000 * 365 = 730,000
        assertThat(result.nutritionMetricResults().get("CALORIES").target())
                .isEqualTo(730_000.0);
        // PROTEIN target = 730,000 * 0.30 = 219,000
        assertThat(result.nutritionMetricResults().get("PROTEIN").target())
                .isEqualTo(219_000.0, within(1e-6));
    }

    @Test
    void selfSufficiencyIsCalorieMidpointOverTarget() {
        SimulationParameters params = new SimulationParameters(crops(), macros(30), 2000d, 365.0);
        SimulationResult result = engine.run(params);

        // midpoint(16,400, 27,600) = 22,000;
        // selfSufficiencyPercentage = (22,000 / 730,000) * 100
        assertThat(result.selfSufficiencyPercentage())
                .isEqualTo((22_000.0 / 730_000.0) * 100, within(1e-9));
    }

    @Test
    void targetRespectsExplicitNonDefaultLength() {
        SimulationParameters params = new SimulationParameters(crops(), macros(50), 2000d, 100.0);
        SimulationResult result = engine.run(params);

        // simCalTarget = 2000 * 100 = 200,000
        assertThat(result.nutritionMetricResults().get("CALORIES").target())
                .isEqualTo(200_000.0);
    }

    @Test
    void nullLengthDefaultsTo365() {
        SimulationParameters params = new SimulationParameters(crops(), macros(30), 2000d, null);
        SimulationResult result = engine.run(params);

        // daily 2000 * default 365 = 730,000
        assertThat(result.nutritionMetricResults().get("CALORIES").target())
                .isEqualTo(730_000.0);
    }
}