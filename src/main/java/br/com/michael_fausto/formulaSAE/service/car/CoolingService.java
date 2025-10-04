package br.com.michael_fausto.formulaSAE.service.car;

import br.com.michael_fausto.formulaSAE.entity.car.CoolingTelemetryEntity;
import br.com.michael_fausto.formulaSAE.exception.SensorFailureException;
import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingData;
import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingSensorData;
import br.com.michael_fausto.formulaSAE.repository.car.CoolingTelemetryRepository;
import br.com.michael_fausto.formulaSAE.util.ConvertUtills;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@Controller
public class CoolingService {

    private final CoolingTelemetryRepository repository;

    public CoolingService(CoolingTelemetryRepository repository) {
        this.repository = repository;
    }

    public CoolingData teste() {
        Random random = new Random();
        Integer coolingSystemTemperature = 20 + random.nextInt(100);
        Double reservoirVolume = 0.5 + random.nextDouble(17);
        Boolean fan = random.nextBoolean();

        return new CoolingData(coolingSystemTemperature, reservoirVolume, fan);
    }

    public CoolingData coolingSensorParse(CoolingSensorData sensorData) {
        if (sensorData == null) throw new SensorFailureException("CoolingSensorOFF");

        Function<String, Integer> safeIntConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.intConvert(value);
        Function<String, Double> safeDoubleConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.doubleConvert(value);
        Function<String, Boolean> safeBooleanConvert = value ->
                (value == null || value.isEmpty()) ? null : ConvertUtills.booleanConvert(value);

        return new CoolingData(
                safeIntConvert.apply(sensorData.coolingSystemTemperature()),
                safeDoubleConvert.apply(sensorData.reservoirVolume()),
                safeBooleanConvert.apply(sensorData.fan()));
    }

    public ComponentStatus setCoolingTemperatureStatus(CoolingData coolingData) {
        Function<Integer, ComponentStatus> coolingLambda = temp -> {
            if (temp == null) return ComponentStatus.FAILURE;
            if (temp < 105) return ComponentStatus.NORMAL;
            if (temp < 115) return ComponentStatus.HIGH;
            return ComponentStatus.WARNING;
        };

        return coolingLambda.apply(coolingData.coolingSystemTemperature());
    }

    public ComponentStatus setCoolingReservoirVolumeStatus(CoolingData coolingData) {
        Function<Integer, ComponentStatus> coolingLambda = temp -> {
            if (temp == null) return ComponentStatus.FAILURE;
            if (temp < 1.2) return ComponentStatus.LOW;
            if (temp <= 1.6) return ComponentStatus.NORMAL;
            return ComponentStatus.HIGH;
        };

        return coolingLambda.apply(coolingData.coolingSystemTemperature());
    }

    public Integer setCoolingTemperature(CoolingData coolingData) {
        return coolingData.coolingSystemTemperature();
    }

    public Double setCoolingFluidVolume(CoolingData coolingData) {
        return coolingData.reservoirVolume();
    }

    public Boolean setFan(CoolingData coolingData) {
        return coolingData.fan();
    }

    public CoolingTelemetryEntity setCoolingEntity(CoolingData coolingData) {
        return new CoolingTelemetryEntity(
                null,
                setCoolingTemperature(coolingData),
                setCoolingTemperatureStatus(coolingData),
                setCoolingFluidVolume(coolingData),
                setCoolingReservoirVolumeStatus(coolingData),
                LocalDateTime.now(),
                setFan(coolingData));
    }

    public void saveCoolingTelemetryEntity(CoolingTelemetryEntity data) {
        repository.save(data);
    }

    public CoolingTelemetryEntity getLatestTelemetry() {
        return repository.findTopByOrderByIdDesc();
    }
}
