package com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.service;


import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.Nutrition;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.entity.VarietyProfile;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.domain.valueobject.Yield;
import com.daniellaprade1.self_sufficiency_simulation.features.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.application.dto.MacroDistributionRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.MacroDistributionPreset;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.enums.NutritionMetric;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.NutritionTotalsDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.valueobject.CropData;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.CropRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.domain.engine.impl.SimulationEngineImpl;
import com.daniellaprade1.self_sufficiency_simulation.features.simulation.application.service.impl.SimulationServiceImpl;
import com.daniellaprade1.self_sufficiency_simulation.features.nutrition.domain.valueobject.MacroDistribution;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SimulationServiceTest {

    @Mock
    private VarietyRepository varietyRepository;
    @Mock
    private SimulationEngineImpl simulationEngine;
    @Spy
    @InjectMocks
    private SimulationServiceImpl simulationService;

    @Test
    void shouldMapCropInputDTOToSimulationCropData() {
        // Arrange
        UUID varietyID = UUID.randomUUID();
        double cropUnits = 10d;

        double kcalPerGram = 0.5;
        double proteinPerGram = 1;
        double totalFatPerGram = 1;
        double totalCarbsPerGram = 1;
        Nutrition nutrition = new Nutrition(
                kcalPerGram,
                proteinPerGram,
                totalFatPerGram,
                totalCarbsPerGram
        );

        double minGrams = 10d;
        double maxGrams = 20d;
        Yield yield = new Yield(
                minGrams,
                maxGrams
        );

        CropRequestDTO input = new CropRequestDTO(varietyID, cropUnits);

        VarietyProfile profile = new VarietyProfile(
                UUID.randomUUID(),
                nutrition,
                yield,
                null
        );
        Variety variety = new Variety(
                varietyID,
                "TestVariety",
                null,
                profile
        );
        when(varietyRepository.findById(varietyID)).thenReturn(Optional.of(variety));

        // Act
        CropData result = simulationService.toCropData(input);

        // Assert
        assertEquals(cropUnits, result.units());
        assertEquals(kcalPerGram, result.nutrition().getKcalPerGram());
        assertEquals(minGrams, result.nutrition().getKcalPerGram());
        assertEquals(maxGrams, result.nutrition().getKcalPerGram());
    }

    @Test
    void shouldPassMappedCropDataToSimulationEngine() {
        // Arrange
        double calorieTarget = 2000;
        double cropUnits = 20;

        List<CropRequestDTO> cropInputs = new ArrayList<>();
        List<CropData> engineCropData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CropRequestDTO input = new CropRequestDTO(UUID.randomUUID(), cropUnits);
            cropInputs.add(input);

            double mappedUnits = 100;
            Nutrition mappedNutrition = new Nutrition(0.1, 0d, 0d, 0d);
            Yield mappedYield = new Yield(10d, 30d);
            // Force mapping function to return dummy data
            CropData mapped = new CropData(
                    mappedUnits,
                    mappedNutrition,
                    mappedYield
            );
            doReturn(mapped)
                    .when(simulationService)
                    .toCropData(input);
            engineCropData.add(mapped);
        }
        MacroDistributionRequestDTO macroDistributionInput = new MacroDistributionRequestDTO(MacroDistributionPreset.KETO, null);
        MacroDistribution engineDistribution = MacroDistributionPreset.KETO.getDistribution();

        SimulationRequestDTO request = new SimulationRequestDTO(calorieTarget, cropInputs, macroDistributionInput);

        double caloriesProduced = 2000;
        Map<NutritionMetric, Double> nutritionTotals = Map.of(NutritionMetric.CALORIES, caloriesProduced);
        double selfSufficiencyPercentage = 100;

        SimulationResponseDTO expected = new SimulationResponseDTO(new NutritionTotalsDTO(nutritionTotals), selfSufficiencyPercentage);

        when(simulationEngine.run(engineCropData, engineDistribution, calorieTarget))
                .thenReturn(expected);

        // Act
        SimulationResponseDTO result = simulationService.runSimulation(request);

        // Assert
        verify(simulationEngine).run(engineCropData, engineDistribution, calorieTarget);
        assertEquals(expected, result);
    }
}
