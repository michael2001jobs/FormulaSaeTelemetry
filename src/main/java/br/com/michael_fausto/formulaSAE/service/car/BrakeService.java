package br.com.michael_fausto.formulaSAE.service.car;

import br.com.michael_fausto.formulaSAE.exception.SensorFailureException;
import br.com.michael_fausto.formulaSAE.mapper.BrakeMapper;
import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeData;
import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;
import br.com.michael_fausto.formulaSAE.entity.car.BrakeEntity;
import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import br.com.michael_fausto.formulaSAE.model.dto.BrakeDTO;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.BrakeMqttSubscriber;
import br.com.michael_fausto.formulaSAE.repository.car.BrakeTelemetryRepository;
import br.com.michael_fausto.formulaSAE.util.ConvertUtills;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Function;

@AllArgsConstructor
@Service
public class BrakeService {

    private final BrakeTelemetryRepository repository;
    private final BrakeMqttSubscriber subscriber;
    private final BrakeMapper mapper;

    public BrakeEntity buildBrakeEntity(BrakeData brakeData) {
        return repository.save(new BrakeEntity(
                null,
                setDiscTemperature(brakeData),
                setDiscTemperatureStatus(brakeData),
                setFluidPressure(brakeData),
                setFluidPressureStatus(brakeData),
                LocalDateTime.now(),
                setHandBrake(brakeData)));
    }

    public BrakeDTO getLatestTelemetry() {
        return mapper.toDto(repository.findTopByOrderByIdDesc());
    }

    @Transactional
    public BrakeEntity mqttBrake() {
        return buildBrakeEntity(brakeSensorParse(subscriber.getLast()));
    }

    public BrakeData brakeSensorParse(BrakeSensorData sensorData) {
        if (sensorData == null) throw new SensorFailureException("BreakSystemOFF");

        Function<String, Integer> safeIntConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.intConvert(value);
        Function<String, Boolean> safeBooleanConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.booleanConvert(value);

        return new BrakeData(
                safeIntConvert.apply(sensorData.discBreakTemperature()),
                safeIntConvert.apply(sensorData.brakeFluidPressure()),
                safeBooleanConvert.apply(sensorData.handBrake())
        );
    }

    public ComponentStatus setDiscTemperatureStatus(BrakeData brakeData) {
        Function<Integer, ComponentStatus> brakeLambda = temp -> {
            if (temp == null) return ComponentStatus.FAILURE;
            if (temp < 250) return ComponentStatus.NORMAL;
            if (temp < 400) return ComponentStatus.HIGH;
            return ComponentStatus.WARNING;
        };

        return brakeLambda.apply(brakeData.discTemperature());
    }

    public ComponentStatus setFluidPressureStatus(BrakeData brakeData) {
        Function<Integer, ComponentStatus> brakeFluidPressureLambda = pressure -> {
            if (pressure == null) return ComponentStatus.FAILURE;
            if (pressure < 40) return ComponentStatus.NORMAL;
            if (pressure < 70) return ComponentStatus.HIGH;
            return ComponentStatus.WARNING;
        };
        return brakeFluidPressureLambda.apply(brakeData.fluidPressure());
    }

    public Integer setDiscTemperature(BrakeData brakeData) {
        return brakeData.discTemperature();
    }

    public Integer setFluidPressure(BrakeData brakeData) {
        return brakeData.fluidPressure();
    }

    public Boolean setHandBrake(BrakeData brakeData) {
        return brakeData.handBrake();
    }
}
