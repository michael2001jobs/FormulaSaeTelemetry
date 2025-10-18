package br.com.michael_fausto.formulaSAE.service.car;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.exception.CarException;
import br.com.michael_fausto.formulaSAE.mapper.car.CarMapper;
import br.com.michael_fausto.formulaSAE.model.car.CarDTO;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.CarRepository;
import br.com.michael_fausto.formulaSAE.service.car.brake.BrakeSetupService;
import br.com.michael_fausto.formulaSAE.service.car.cooling.CoolingSetupService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardException;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final CarMapper mapper;

    private final BrakeSetupService brakeSetupService;
    private final CoolingSetupService coolingSetupService;

    public CarEntity buildCar(CarDTO dto) {
        return new CarEntity(
                null,
                dto.name(),
                null,
                null);
    }

    public void carIsCompleted(CarEntity entity) {
        if (entity.getBrakeSetup() == null || entity.getCoolingSetup() == null)
            throw new CarException("This car is not configured");
    }

    public void setCollingSetup(CarEntity car, CoolingSetupDTO setup) {
        car.setCoolingSetup(coolingSetupService.buildCoolingSetup(setup));
    }

    public void setBrakeSetup(CarEntity car, BrakeSetupDTO setup) {
        car.setBrakeSetup(brakeSetupService.buildBrakeSetup(setup));
    }

    public void saveEntity(CarEntity car) {
        repository.save(car);
    }

    public CarEntity findById(Long id) {
        CarEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cooling Setup with" + id + " not found"));
        return entity;
    }
}
