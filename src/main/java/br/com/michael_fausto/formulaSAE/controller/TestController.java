package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.mapper.car.brake.BrakeSetupMapper;
import br.com.michael_fausto.formulaSAE.service.car.brake.BrakeSetupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {

    private BrakeSetupMapper brakeSetupMapper;
    private BrakeSetupService brakeSetupService;

}

