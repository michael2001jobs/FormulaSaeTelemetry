package br.com.michael_fausto.formulaSAE.controller.car;


import br.com.michael_fausto.formulaSAE.service.car.BrakeTelemetryService;
import org.springframework.web.bind.annotation.*;


@RestController
public class BrakeController {

    BrakeTelemetryService brakeTelemetryService;

    public BrakeController(BrakeTelemetryService brakeTelemetryService) {
        this.brakeTelemetryService = brakeTelemetryService;
    }


}

