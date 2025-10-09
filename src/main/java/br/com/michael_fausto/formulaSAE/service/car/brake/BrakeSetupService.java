package br.com.michael_fausto.formulaSAE.service.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.brake.BrakeSetupMapper;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.brake.BrakeSetupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrakeSetupService {

    private final BrakeSetupRepository repository;
    private final BrakeSetupMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(BrakeSetupService.class);

    public BrakeSetupEntity buildBrakeSetup(BrakeSetupDTO dto) {
        BrakeSetupEntity buildEntity = mapper.toEntity(dto);
        logger.info("BrakeSetup convert in build setup: {}", buildEntity);
        return buildEntity;
    }

    public void saveEntity(BrakeSetupEntity entity) {
        repository.save(entity);
        logger.info("BrakeSetup saved: {}", entity);
    }

    public BrakeSetupDTO convertDto(BrakeSetupEntity entity) {
        BrakeSetupDTO dto =  mapper.toDto(entity);
        logger.info("BrakeSetup DTO convert : {}", dto);
        return dto;
    }

    public BrakeSetupEntity convertEntity(BrakeSetupDTO dto) {
        BrakeSetupEntity entity = mapper.toEntity(dto);
        logger.info("BrakeSetup ENTITY convert : {}", entity);
        return entity;
    }

    public BrakeSetupEntity getBrakeSetupEntity(Long id) {
        BrakeSetupEntity entity =  repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Brake Setup with" + id + " not found"));
        logger.info("BrakeSetupEntity find by id : {}", entity);
        return entity;
    }

    public BrakeSetupEntity updateBrakeSetupEntity(BrakeSetupDTO dto, Long id) {
        BrakeSetupEntity entity = getBrakeSetupEntity(id);

        entity.setProfileName(dto.profileName());
        entity.setNormalDiscTemperature(dto.normalDiscTemperature());
        entity.setHighDiscTemperature(dto.highDiscTemperature());
        entity.setNormalFluidPressure(dto.normalFluidPressure());
        entity.setHighFluidPressure(dto.highFluidPressure());

        repository.save(entity);
        logger.info("BrakeSetup update: {}", entity);

        return entity;
    }

    public void deleteBrakeSetup(Long id) {
        try {
            repository.deleteById(id);
            logger.info("Brake Setup as been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Brake Setup with" + id + " not found");
        }
    }

    public boolean isTableEmpty() {
        return repository.existsBy();
    }
}
