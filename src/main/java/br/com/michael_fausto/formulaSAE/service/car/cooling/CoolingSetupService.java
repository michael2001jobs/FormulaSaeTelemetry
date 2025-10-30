package br.com.michael_fausto.formulaSAE.service.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.mapper.car.cooling.CoolingSetupMapper;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import br.com.michael_fausto.formulaSAE.repository.car.cooling.CoolingSetupRepository;
import br.com.michael_fausto.formulaSAE.service.interfaces.SetupCarInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CoolingSetupService implements SetupCarInterface<CoolingSetupDTO> {

    private final CoolingSetupMapper mapper;
    private final CoolingSetupRepository repository;
    private final Logger logger = LoggerFactory.getLogger(CoolingSetupService.class);


    @Override
    public CoolingSetupDTO create(CoolingSetupDTO dto) {
        CoolingSetupEntity entity = mapper.toEntity(dto);
        entity.setCreateAt(LocalDateTime.now());

        repository.save(entity);

        logger.debug("CoolingSetup convert in entity : {}", entity);
        return dto;
    }

    @Override
    public CoolingSetupDTO delete(String name) {
        try {
            repository.deleteByName(name);
            logger.info("CoolingSetup as been deleted");
            return mapper.toDto(repository.findByName(name));

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Cooling Setup not found");
        }
    }

    @Override
    public CoolingSetupDTO update(CoolingSetupDTO dto, String name) {
        CoolingSetupEntity entity = repository.findByName(name);

        entity.setName(dto.name());
        entity.setNormalCoolingTemperature(dto.normalCoolingTemperature());
        entity.setHighCoolingTemperature(dto.highCoolingTemperature());
        entity.setNormalReservoirVolume(dto.normalReservoirVolume());
        entity.setLowReservoirVolume(dto.lowReservoirVolume());

        repository.save(entity);
        logger.info("CoolingSetup update: {}", entity);

        return dto;
    }

    @Override
    public CoolingSetupDTO findByName(String name) {
        return mapper.toDto(repository.findByName(name));
    }


    @Override
    public Boolean isEmpty() {
        return repository.existsBy();
    }

    @Override
    public List<CoolingSetupDTO> showAll() {
        return mapper.listDto(repository.findAll());
    }
}
