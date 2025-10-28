package br.com.michael_fausto.formulaSAE.service.telemetry;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.entity.telemetry.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.entity.car.CarEntity;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.mapper.telemetry.TelemetryMapper;
import br.com.michael_fausto.formulaSAE.model.telemetry.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.repository.telemetry.TelemetryRepository;
import br.com.michael_fausto.formulaSAE.service.users.UserService;
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
    private final UserService userService;

    public TelemetryEntity buildTelemetryList(UsersEntity user, CarEntity car) {return new TelemetryEntity(user, car);}

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

}
