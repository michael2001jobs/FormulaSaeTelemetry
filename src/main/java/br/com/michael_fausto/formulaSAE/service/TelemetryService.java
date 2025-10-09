package br.com.michael_fausto.formulaSAE.service;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.mapper.TelemetryMapper;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeDTO;
import br.com.michael_fausto.formulaSAE.model.telemetry.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.repository.TelemetryRepository;
import br.com.michael_fausto.formulaSAE.service.car.brake.BrakeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@Service
public class TelemetryService {

    private final TelemetryRepository repository;
    private final TelemetryMapper mapper;
    private final PilotService pilotService;
    private final BrakeService brakeService;

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

    public TelemetryDTO entityToDTO(TelemetryEntity telemetry) {
        return mapper.toDto(telemetry);
    }

    @Transactional
    public TelemetryEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Error, invalid Telemetry"));
    }

    public BrakeDTO brakeMqttReading(TelemetryEntity entity, BrakeSetupEntity setup) {
        entity.getBrakeTelemetry().add(brakeService.mqttBrakeReady(setup));
        return brakeService.getLatestTelemetry();
    }
}
