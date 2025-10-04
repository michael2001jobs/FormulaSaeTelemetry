package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.controller.car.BrakeInputDataController;
import br.com.michael_fausto.formulaSAE.controller.car.CoolingInputDataController;
import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.Pilot;
import br.com.michael_fausto.formulaSAE.repository.car.BrakeTelemetryRepository;
import br.com.michael_fausto.formulaSAE.repository.car.CoolingTelemetryRepository;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@Controller
@RestController
public class TelemetryController {

    private final CoolingInputDataController coolingInputDataController;
    private final BrakeInputDataController brakeInputDataController;
    private final CoolingTelemetryRepository coolingRepository;
    private final BrakeTelemetryRepository brakeRepository;
    private final PilotService pilotService;

    @Scheduled(fixedRate = 300)
    public void telemetryData() {
        coolingInputDataController.sendCoolingTelemetry();
        brakeInputDataController.sendBrakeTelemetry();
    }
}
