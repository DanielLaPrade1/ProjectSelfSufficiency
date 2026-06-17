package com.daniellaprade1.self_sufficiency_simulation.modules.simulation.web.controller;

import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.request.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.dto.response.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.modules.simulation.application.service.SimulationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/run")
    public ResponseEntity<SimulationResponseDTO> runSimulation(
            @Valid @RequestBody SimulationRequestDTO request
    ) {
        SimulationResponseDTO response = simulationService.runSimulation(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
