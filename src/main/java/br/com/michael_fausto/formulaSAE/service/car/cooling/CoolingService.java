package br.com.michael_fausto.formulaSAE.service.car.cooling;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingEntity;
import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.exception.SensorFailureException;
import br.com.michael_fausto.formulaSAE.mapper.car.cooling.CoolingMapper;
import br.com.michael_fausto.formulaSAE.model.car.ComponentStatus;
import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingData;
import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingSensorData;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingDTO;
import br.com.michael_fausto.formulaSAE.mqtt.car.cooling.CoolingMqttSubscriber;
import br.com.michael_fausto.formulaSAE.repository.car.cooling.CoolingRepository;
import br.com.michael_fausto.formulaSAE.util.ConvertUtills;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.function.Function;

@AllArgsConstructor
@Controller
public class CoolingService {

    private final CoolingRepository repository;
    private final CoolingMqttSubscriber subscriber;
    private final CoolingMapper mapper;
    private final CoolingSetupService setupService;
    private final Logger logger = LoggerFactory.getLogger(CoolingService.class);

    public CoolingEntity buildCoolingEntity(CoolingData coolingData, CoolingSetupEntity setup) {
        if (!setupService.isTableEmpty()) {
            return new CoolingEntity(
                    null,
                    coolingData.coolingSystemTemperature(),
                    setCoolingTemperatureStatus(coolingData, setup),
                    coolingData.reservoirVolume(),
                    setCoolingReservoirVolumeStatus(coolingData, setup),
                    LocalDateTime.now(),
                    coolingData.fan());
        }else {
            throw new EntityNotFoundException("No CoolingSetup in database");
        }
    }

    public CoolingDTO getLatestTelemetry() {
        return mapper.toDto(repository.findTopByOrderByIdDesc());
    }

    @Transactional
    public CoolingEntity mqttCooling(CoolingSetupEntity setup) {
        return buildCoolingEntity(coolingSensorParse(subscriber.getLast()), setup);
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

    public ComponentStatus setCoolingTemperatureStatus(CoolingData coolingData, CoolingSetupEntity setup) {
        Function<Integer, ComponentStatus> coolingLambda = temp -> {
            if (temp == null) return ComponentStatus.FAILURE;
            if (temp < setup.getNormalCoolingTemperature()) return ComponentStatus.NORMAL;
            if (temp < setup.getHighCoolingTemperature()) return ComponentStatus.HIGH;
            return ComponentStatus.WARNING;
        };

        return coolingLambda.apply(coolingData.coolingSystemTemperature());
    }

    public ComponentStatus setCoolingReservoirVolumeStatus(CoolingData coolingData, CoolingSetupEntity setup) {
        Function<Integer, ComponentStatus> coolingLambda = temp -> {
            if (temp == null) return ComponentStatus.FAILURE;
            if (temp < setup.getLowReservoirVolume()) return ComponentStatus.LOW;
            if (temp <= setup.getNormalReservoirVolume()) return ComponentStatus.NORMAL;
            return ComponentStatus.HIGH;
        };

        return coolingLambda.apply(coolingData.coolingSystemTemperature());
    }
}
