package br.com.michael_fausto.formulaSAE.service;

import br.com.michael_fausto.formulaSAE.mapper.PilotMapper;
import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.Pilot;
import br.com.michael_fausto.formulaSAE.repository.PilotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

    private final PilotRepository repository;
    private final PilotMapper mapper;

    public PilotService(PilotRepository repository, PilotMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public PilotEntity createPilot(Pilot pilot) {
        return repository.save(mapper.convertToEntity(pilot));
    }

    public PilotEntity getPilot(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pilot not found"));
    }

    public void deletePilot(PilotEntity pilot) {
        try {
            repository.deleteById(pilot.getId());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Pilot not found");
        }
    }

    public PilotEntity updatePilot(Long id, Pilot pilot) {
        PilotEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pilot not found"));
        entity.setEmail(pilot.getEmail());
        entity.setName(pilot.getName());
        return repository.save(entity);
    }

}
