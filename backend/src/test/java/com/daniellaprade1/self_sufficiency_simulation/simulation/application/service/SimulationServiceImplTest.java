package com.daniellaprade1.self_sufficiency_simulation.simulation.application.service;

import com.daniellaprade1.self_sufficiency_simulation.modules.crop.domain.entity.Variety;
import com.daniellaprade1.self_sufficiency_simulation.modules.crop.infra.persistence.VarietyRepository;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.MacroDistributionRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.application.service.MacroService;
import com.daniellaprade1.self_sufficiency_simulation.modules.nutrition.domain.valueobject.MacroDistribution;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.CropRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input.CropInputMapper;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.input.MacroDistributionInputMapper;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.mapper.output.SimulationResponseMapper;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.service.impl.SimulationServiceImpl;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.engine.SimulationEngine;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.CropInput;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.MacroDistributionInput;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.parameters.SimulationParameters;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.domain.valueobject.result.SimulationResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimulationServiceImplTest {

    @Mock
    VarietyRepository varietyRepository;
    @Mock
    SimulationEngine simulationEngine;
    @Mock
    MacroService macroService;
    @Mock
    CropInputMapper cropInputMapper;
    @Mock
    MacroDistributionInputMapper macroDistributionInputMapper;
    @Mock
    SimulationResponseMapper simulationResponseMapper;

    @InjectMocks
    SimulationServiceImpl service;

    @Test
    void assemblesParametersAndDelegates() {

        // Assemble

        UUID idA = UUID.randomUUID();
        UUID idB = UUID.randomUUID();

        var reqA = new CropRequestDTO(idA, 3.0);
        var reqB = new CropRequestDTO(idB, 7.0);
        var macroDto = mock(MacroDistributionRequestDTO.class);
        var request = new SimulationRequestDTO( 2000.0, 200.0, macroDto, List.of(reqA, reqB));

        var varietyA = mock(Variety.class);
        when(varietyA.getId()).thenReturn(idA);
        var varietyB = mock(Variety.class);
        when(varietyB.getId()).thenReturn(idB);

        when(varietyRepository.findAllById(anyList()))
                .thenReturn(List.of(varietyA, varietyB));

        var cropInputA = mock(CropInput.class);
        when(cropInputMapper.toCropInput(varietyA, 3.0)).thenReturn(cropInputA);
        var cropInputB = mock(CropInput.class);
        when(cropInputMapper.toCropInput(varietyB, 7.0)).thenReturn(cropInputB);

        var macroDist = mock(MacroDistribution.class);
        when(macroService.resolveMacroDistribution(macroDto)).thenReturn(macroDist);
        var macroInput = mock(MacroDistributionInput.class);
        when(macroDistributionInputMapper.toMacroDistributionInput(macroDist)).thenReturn(macroInput);

        var engineResult = mock(SimulationResult.class);
        var responseDto = mock(SimulationResponseDTO.class);
        when(simulationEngine.run(any())).thenReturn(engineResult);
        when(simulationResponseMapper.toNutritionResponseDTO(engineResult)).thenReturn(responseDto);

        // Act

        SimulationResponseDTO out = service.runSimulation(request);

        // Assert

        assertThat(out).isSameAs(responseDto);

        var captor = ArgumentCaptor.forClass(SimulationParameters.class);
        verify(simulationEngine).run(captor.capture());
        var params = captor.getValue();

        // parameter order preserved, units matched to the right variety
        assertThat(params.cropInputs()).containsExactly(cropInputA, cropInputB);
        assertThat(params.macroDistributionInput()).isSameAs(macroInput);
        assertThat(params.dailyCalorieTarget()).isEqualTo(2000.0);
        assertThat(params.simulationLengthDays()).isEqualTo(200.0);
    }

    @Test
    void throwsWhenVarietyIdNotFound() {
        UUID missing = UUID.randomUUID();
        var request = new SimulationRequestDTO(2000.0, 200.0, mock(MacroDistributionRequestDTO.class), List.of(new CropRequestDTO(missing, 1.0)));

        when(varietyRepository.findAllById(anyList())).thenReturn(List.of());

        assertThatThrownBy(() -> service.runSimulation(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(missing.toString());

        verifyNoInteractions(simulationEngine);
    }
}
