package br.com.michael_fausto.formulaSAE.service.car;

import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.entity.car.SetupCarEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.CarMapper;
import br.com.michael_fausto.formulaSAE.model.car.CarDTO;
import br.com.michael_fausto.formulaSAE.repository.car.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository repository;
    private final CarMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(CarService.class);


    public CarDTO create(CarDTO dto, SetupCarEntity setupCar) {
        CarEntity entity = new CarEntity(
                dto.name(),
                dto.versionOrModel(),
                setupCar);

        repository.save(entity);
        logger.debug("Car create");

        return dto;
    }

    @Transactional
    public CarDTO delete(String name) {
        try {
            repository.deleteByName(name);
            logger.info(("Car as been delete"));

            return mapper.toDto(repository.findByName(name));
        } catch (EmptyResultDataAccessException e) {
            throw  new EntityNotFoundException("Car not found");
        }
    }

    @Transactional
    public CarDTO update(CarDTO dto, String name) {
        CarEntity entity = repository.findByName(name);

        entity.setName(dto.name());
        entity.setVersionOrModel(dto.versionOrModel());

        repository.save(entity);

        return dto;
    }

    public CarDTO findByName(String name) {
        return mapper.toDto(repository.findByName(name));
    }

    public Boolean isEmpty() {
        return repository.existsBy();
    }

    public List<CarDTO> showAll() {
        return mapper.listDto(repository.findAll());
    }
}
