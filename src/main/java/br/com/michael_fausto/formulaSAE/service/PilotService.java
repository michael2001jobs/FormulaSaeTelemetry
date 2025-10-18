package br.com.michael_fausto.formulaSAE.service;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.mapper.PilotMapper;
import br.com.michael_fausto.formulaSAE.model.pilot.PilotDTO;
import br.com.michael_fausto.formulaSAE.repository.PilotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class PilotService {

    private final PilotRepository repository;
    private final PilotMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(PilotService.class);

    public PilotDTO convertDto(PilotEntity entity) {
        PilotDTO dto = mapper.toDto(entity);
        logger.info("Pilot convert in DTO: {}", dto);
        return dto;
    }

    public void savePilot(PilotEntity entity) {
        repository.save(entity);
        logger.info("Pilot saved: {}", entity);
    }

    public PilotEntity convertEntity(PilotDTO dto) {
        PilotEntity entity = mapper.toEntity(dto);
        logger.info("Pilot convert in Entity : {}", entity);
        return entity;
    }

    @Transactional
    public PilotEntity findById(Long id) {
        PilotEntity entity = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Pilot Setup with " + id + " not found"));
        logger.debug("Pilot find by id : {}", entity);
        return entity;
    }

    @Transactional
    public PilotDTO updatePilot(PilotDTO dto, Long id) {
        PilotEntity entity = findById(id);

        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());

        repository.save(entity);
        logger.info("Pilot update: {}", entity);

        return mapper.toDto(entity);
    }


    @Transactional
    public void deletePilot(Long id) {
        try {
            repository.deleteById(id);
            logger.info("Pilot as been deleted");
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Pilot with" + id + " not found");
        }
    }
}
    
