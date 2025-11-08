package br.com.michael_fausto.formulaSAE.controller.car;

import br.com.michael_fausto.formulaSAE.service.car.CarService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService service;

}
