package br.com.michael_fausto.formulaSAE.service;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.entity.car.BrakeEntity;
import br.com.michael_fausto.formulaSAE.entity.car.CoolingEntity;
import br.com.michael_fausto.formulaSAE.mapper.TelemetryMapper;
import br.com.michael_fausto.formulaSAE.model.dto.PilotDTO;
import br.com.michael_fausto.formulaSAE.model.dto.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.repository.TelemetryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TelemetryService {

    private final TelemetryRepository repository;
    private final PilotService pilotService;
    private final TelemetryMapper mapper;

    public TelemetryEntity buildTelemetryList(PilotEntity pilot) {
        return new TelemetryEntity(
                null,
                pilot,
                LocalDateTime.now(),
                new ArrayList<BrakeEntity>(),
                new ArrayList<CoolingEntity>()
        );
    }

    public void saveList(TelemetryEntity entity) {
        repository.save(entity);
    }

    public TelemetryDTO EntityToDTO(TelemetryEntity telemetry) {
        return mapper.toDto(telemetry);
    }

    public TelemetryEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->new EntityNotFoundException("Error, invalid Telemetry"));
    }



    public List<TelemetryEntity> getAllTelemetry(Long pilotId) {
        PilotEntity pilotEntity = pilotService.getPilotEntity(pilotId);
        return pilotEntity.getTelemetryList();
    }
}
