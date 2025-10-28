package br.com.michael_fausto.formulaSAE.controller.car;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.model.car.CarDTO;
import br.com.michael_fausto.formulaSAE.service.car.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService service;

    @PostMapping("/create&car")
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO car) {
        CarEntity entity = service.buildCar(car);
        return ResponseEntity.ok(service.convertDTO(entity));
    }

    @GetMapping("/get&car&by&name")
    public ResponseEntity<CarDTO> getCar(@RequestBody String name) {
        CarEntity entity = service.findByName(name);
        return ResponseEntity.ok(service.convertDTO(entity));
    }


}
