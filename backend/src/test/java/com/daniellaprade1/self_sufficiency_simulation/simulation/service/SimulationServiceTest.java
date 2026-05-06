package com.daniellaprade1.self_sufficiency_simulation.simulation.service;


import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.crop.domain.entity.VarietyProfile;
import com.daniellaprade1.self_sufficiency_simulation.crop.repository.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.SimulationCropData;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.CropInputDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.engine.impl.SimulationEngineImpl;
import com.daniellaprade1.self_sufficiency_simulation.simulation.service.impl.SimulationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        double yieldMinGrams = 10d;
        double yieldMaxGrams = 20d;

        CropInputDTO input = new CropInputDTO(varietyID, cropUnits);

        VarietyProfile profile = new VarietyProfile(
                UUID.randomUUID(),
                kcalPerGram,
                yieldMinGrams,
                yieldMaxGrams,
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
        SimulationCropData result = simulationService.toSimulationCropData(input);

        // Assert
        assertEquals(cropUnits, result.units());
        assertEquals(kcalPerGram, result.kcalPerGram());
        assertEquals(yieldMinGrams, result.yieldMin());
        assertEquals(yieldMaxGrams, result.yieldMax());
    }

    @Test
    void shouldPassMappedCropDataToSimulationEngine() {
        // Arrange
        double calorieTarget = 2000;
        double cropUnits = 20;

        List<CropInputDTO> cropInputs = new ArrayList<>();
        List<SimulationCropData> engineCropData = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CropInputDTO input = new CropInputDTO(UUID.randomUUID(), cropUnits);
            cropInputs.add(input);
            // Force mapping function to return dummy data
            SimulationCropData mapped = new SimulationCropData(1d, 1d, 1d, 1d);
            doReturn(mapped)
                    .when(simulationService)
                    .toSimulationCropData(input);
            engineCropData.add(mapped);
        }
        SimulationRequestDTO request = new SimulationRequestDTO(calorieTarget, cropInputs);

        double caloriesProduced = 2000;
        double selfSufficiencyPercentage = 100;
        SimulationResponseDTO expected = new SimulationResponseDTO(caloriesProduced, selfSufficiencyPercentage);

        when(simulationEngine.run(engineCropData, calorieTarget))
                .thenReturn(expected);

        // Act
        SimulationResponseDTO result = simulationService.runSimulation(request);

        // Assert
        verify(simulationEngine).run(engineCropData, calorieTarget);
        assertEquals(expected, result);
    }

}
