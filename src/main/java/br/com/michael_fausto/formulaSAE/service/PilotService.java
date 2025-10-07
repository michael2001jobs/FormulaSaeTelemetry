package br.com.michael_fausto.formulaSAE.service;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.mapper.PilotMapper;
import br.com.michael_fausto.formulaSAE.model.dto.PilotDTO;
import br.com.michael_fausto.formulaSAE.repository.PilotRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class PilotService {

    private final PilotRepository repository;
    private final PilotMapper mapper;

    public PilotEntity buildPilot(PilotDTO pilotDTO) {
        return new PilotEntity(
                null,
                pilotDTO.getName(),
                pilotDTO.getEmail(),
                new ArrayList<TelemetryEntity>()
        );
    }

    public PilotDTO entityToDTO(PilotEntity pilot) {
        return mapper.toDto(pilot);
    }

    public void savePilot(PilotEntity pilot) {
        repository.save(pilot);
    }


    public PilotEntity getPilotEntity(Long pilotId) {
        return repository.findById(pilotId)
                .orElseThrow(() -> new EntityNotFoundException("Pilot not found"));
    }

    @Transactional
    public PilotDTO getPilotDTO(Long pilotId) {
        PilotEntity pilotEntity = repository.findById(pilotId).orElseThrow();
        return mapper.toDto(pilotEntity);
    }

    @Transactional
    public PilotDTO deletePilot(Long pilotId) {
        try {
            PilotEntity pilot = getPilotEntity(pilotId);
            PilotDTO pilotDTO =entityToDTO(pilot);
            repository.deleteById(pilot.getId());
            return (pilotDTO);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    public PilotDTO updatePilot(Long id, PilotDTO pilotDTO) {
        PilotEntity entity = getPilotEntity(id);
        entity.setEmail(pilotDTO.getEmail());
        entity.setName(pilotDTO.getName());
        repository.save(entity);
        return entityToDTO(entity);
    }
}
