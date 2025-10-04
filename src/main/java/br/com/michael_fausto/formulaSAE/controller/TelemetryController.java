package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.controller.car.BrakeInputDataController;
import br.com.michael_fausto.formulaSAE.controller.car.CoolingInputDataController;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TelemetryController {

    private final CoolingInputDataController coolingInputDataController;
    private final BrakeInputDataController brakeInputDataController;

    public TelemetryController(CoolingInputDataController coolingInputDataController, BrakeInputDataController brakeInputDataController) {
        this.coolingInputDataController = coolingInputDataController;
        this.brakeInputDataController = brakeInputDataController;
    }

    @Scheduled(fixedRate = 5)
    public void telemetryData() {
        coolingInputDataController.sendCoolingTelemetry();
        brakeInputDataController.sendBrakeTelemetry();
    }
}
