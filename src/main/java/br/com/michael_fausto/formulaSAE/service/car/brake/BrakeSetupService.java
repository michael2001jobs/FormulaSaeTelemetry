package br.com.michael_fausto.formulaSAE.service.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.brake.BrakeSetupMapper;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.brake.BrakeSetupRepository;
import br.com.michael_fausto.formulaSAE.service.interfaces.SetupCarInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class BrakeSetupService implements SetupCarInterface<BrakeSetupDTO> {

    private final BrakeSetupRepository repository;
    private final BrakeSetupMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(BrakeSetupService.class);


    @Override
    public BrakeSetupDTO create(BrakeSetupDTO dto) {
        BrakeSetupEntity entity = mapper.toEntity(dto);
        entity.setCreateAt(LocalDateTime.now());

        repository.save(entity);

        logger.debug("BrakeSetup convert in build setup: {}", entity);
        return dto;
    }

    @Override
    @Transactional
    public BrakeSetupDTO delete(String name) {
        try {
            repository.deleteByName(name);
            logger.info("Brake Setup as been deleted");

            return mapper.toDto(repository.findByName(name));

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Brake Setup not found");
        }
    }

    @Override
    @Transactional
    public BrakeSetupDTO update(BrakeSetupDTO dto, String name) {
        BrakeSetupEntity entity = repository.findByName(name);

        entity.setName(dto.name());
        entity.setNormalDiscTemperature(dto.normalDiscTemperature());
        entity.setHighDiscTemperature(dto.highDiscTemperature());
        entity.setNormalFluidPressure(dto.normalFluidPressure());
        entity.setHighFluidPressure(dto.highFluidPressure());

        repository.save(entity);
        logger.info("BrakeSetup update: {}", entity);

        return dto;
    }

    @Override
    public BrakeSetupDTO findByName(String name) {
        return mapper.toDto(repository.findByName(name));
    }

    @Override
    public Boolean isEmpty() {
        return repository.existsBy();
    }

    @Override
    public List<BrakeSetupDTO> showAll() {
        return mapper.listDto(repository.findAll());
    }
}
