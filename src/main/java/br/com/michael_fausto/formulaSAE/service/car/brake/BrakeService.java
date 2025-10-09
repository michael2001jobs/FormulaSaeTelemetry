package br.com.michael_fausto.formulaSAE.service.car.brake;

import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeSetupEntity;
import br.com.michael_fausto.formulaSAE.exception.SensorFailureException;
import br.com.michael_fausto.formulaSAE.mapper.car.brake.BrakeMapper;
import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeData;
import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;
import br.com.michael_fausto.formulaSAE.entity.car.brake.BrakeEntity;
import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeDTO;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.BrakeMqttSubscriber;
import br.com.michael_fausto.formulaSAE.repository.car.brake.BrakeRepository;
import br.com.michael_fausto.formulaSAE.util.ConvertUtills;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class BrakeService {

    private final BrakeRepository repository;
    private final BrakeMqttSubscriber subscriber;
    private final BrakeMapper mapper;
    private final BrakeSetupService setupService;
    private final Logger logger = LoggerFactory.getLogger(BrakeService.class);

    public BrakeEntity buildBrakeEntity(BrakeData brakeData, BrakeSetupEntity setup) {
        if (!setupService.isTableEmpty())
            return new BrakeEntity(
                    null,
                    brakeData.wheelPosition(),
                    brakeData.discTemperature(),
                    setDiscTemperatureStatus(brakeData, setup),
                    brakeData.fluidPressure(),
                    setFluidPressureStatus(brakeData, setup),
                    LocalDateTime.now());
        else {
            throw new EntityNotFoundException("No BrakeSetup in database");
        }
    }

    public BrakeDTO getLatestTelemetry() {
        return mapper.toDto(repository.findTopByOrderByIdDesc());
    }

    @Transactional
    public  BrakeEntity mqttBrakeReady(BrakeSetupEntity setup){
        return buildBrakeEntity(brakeSensorParse(subscriber.getLast()), setup);
    }

    public BrakeData brakeSensorParse(BrakeSensorData sensorData) {
        if (sensorData == null) throw new SensorFailureException("BreakSystemOFF");

        Function<String, Integer> safeIntConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.intConvert(value);
        Function<String, Boolean> safeBooleanConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.booleanConvert(value);

        return new BrakeData(
                sensorData.wheelPosition(),
                safeIntConvert.apply(sensorData.discBreakTemperature()),
                safeIntConvert.apply(sensorData.brakeFluidPressure())
        );
    }

    public ComponentStatus setDiscTemperatureStatus(BrakeData brakeData, BrakeSetupEntity setup) {
        Function<Integer, ComponentStatus> brakeLambda = temp -> {
            if (temp == null) return ComponentStatus.FAILURE;
            if (temp < setup.getNormalDiscTemperature()) return ComponentStatus.NORMAL;
            if (temp < setup.getHighDiscTemperature()) return ComponentStatus.HIGH;
            return ComponentStatus.WARNING;
        };

        return brakeLambda.apply(brakeData.discTemperature());
    }

    public ComponentStatus setFluidPressureStatus(BrakeData brakeData, BrakeSetupEntity setup) {
        Function<Integer, ComponentStatus> brakeFluidPressureLambda = pressure -> {
            if (pressure == null) return ComponentStatus.FAILURE;
            if (pressure < setup.getNormalFluidPressure()) return ComponentStatus.NORMAL;
            if (pressure < setup.getHighFluidPressure()) return ComponentStatus.HIGH;
            return ComponentStatus.WARNING;
        };
        return brakeFluidPressureLambda.apply(brakeData.fluidPressure());
    }
}
