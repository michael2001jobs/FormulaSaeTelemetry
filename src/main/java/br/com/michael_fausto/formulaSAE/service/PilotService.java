package br.com.michael_fausto.formulaSAE.service;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.entity.TelemetryDataEntity;
import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.model.Pilot;
import br.com.michael_fausto.formulaSAE.repository.PilotRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PilotService {

    private final PilotRepository repository;

    public PilotService(PilotRepository repository) {
        this.repository = repository;
    }

    public PilotEntity createAndSavePilot(Pilot pilot) {
        return new PilotEntity(
                null,
                pilot.getName(),
                pilot.getEmail(),
                new ArrayList<TelemetryEntity>()
        );
    }

    public PilotEntity getPilot(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pilot not found"));
    }

    public void deletePilot(PilotEntity pilot) {
        repository.deleteById(pilot.getId());
    }

    public PilotEntity updatePilot(Long id, Pilot pilot) {
        PilotEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pilot not found"));
        entity.setEmail(pilot.getEmail());
        entity.setName(pilot.getName());
        return repository.save(entity);
    }

}
