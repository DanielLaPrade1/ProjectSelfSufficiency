package com.daniellaprade1.self_sufficiency_simulation.simulation.controller;

import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationRequestDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.domain.dto.SimulationResponseDTO;
import com.daniellaprade1.self_sufficiency_simulation.simulation.service.SimulationService;
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

    @PostMapping("run")
    public SimulationResponseDTO runSimulation(@RequestBody SimulationRequestDTO request) {
        return simulationService.runSimulation(request);
    }
}
