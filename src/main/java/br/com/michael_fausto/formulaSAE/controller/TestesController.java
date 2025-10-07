package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.model.dto.BrakeDTO;
import br.com.michael_fausto.formulaSAE.model.dto.PilotDTO;
import br.com.michael_fausto.formulaSAE.model.dto.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import br.com.michael_fausto.formulaSAE.service.TelemetryService;
import br.com.michael_fausto.formulaSAE.service.car.BrakeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TestesController {

    private final BrakeService brakeService;
    private final TelemetryService telemetryService;
    private final PilotService pilotService;

    @GetMapping("/teste1")
    public BrakeDTO brakeDTO() {
        return brakeService.getLatestTelemetry();
    }

    @GetMapping("/teste2")
    public TelemetryDTO telemetryDTO() {
        return telemetryService.EntityToDTO(telemetryService.findById(2L));
    }
}
