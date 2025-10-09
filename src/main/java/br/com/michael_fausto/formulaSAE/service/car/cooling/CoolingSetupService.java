package br.com.michael_fausto.formulaSAE.service.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.cooling.CoolingSetupMapper;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.cooling.CoolingSetupRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CoolingSetupService {

    private final CoolingSetupMapper mapper;
    private final CoolingSetupRepository repository;
    private final Logger logger = LoggerFactory.getLogger(CoolingSetupService.class);

    public CoolingSetupEntity convertEntity(CoolingSetupDTO dto) {
        CoolingSetupEntity entity = mapper.toEntity(dto);
        logger.info("CoolingSetup convert in entity : {}", entity);
        return entity;
    }

    public void saveEntity(CoolingSetupEntity entity) {
        repository.save(entity);
        logger.info("CoolingSetup saved: {}", entity);
    }

    public CoolingSetupDTO convertDto(CoolingSetupEntity entity) {
        CoolingSetupDTO dto =  mapper.toDto(entity);
        logger.info("CoolingSetup convert in DTO : {}", dto);
        return dto;
    }

    public CoolingSetupEntity findById(Long id) {
        CoolingSetupEntity entity =  repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Cooling Setup with" + id + " not found"));
        logger.info("CoolingSetup find by id : {}", entity);
        return entity;
    }

    public CoolingSetupDTO updateCoolingSetupEntity(CoolingSetupDTO dto, Long id) {
        CoolingSetupEntity entity = findById(id);

        entity.setProfileName(dto.profileName());
        entity.setNormalCoolingTemperature(dto.normalCoolingTemperature());
        entity.setHighCoolingTemperature(dto.highCoolingTemperature());
        entity.setNormalReservoirVolume(dto.normalReservoirVolume());
        entity.setLowReservoirVolume(dto.lowReservoirVolume());

        repository.save(entity);
        logger.info("CoolingSetup update: {}", entity);

        return mapper.toDto(entity);
    }

    public void deleteCoolingSetup(Long id) {
        try {
            repository.deleteById(id);
            logger.info("CoolingSetup as been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("CoolingSetup with" + id + " not found");
        }
    }

   public boolean isTableEmpty() {
        return repository.existsBy();
    }
}
