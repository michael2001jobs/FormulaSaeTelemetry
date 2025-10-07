package br.com.michael_fausto.formulaSAE.mqtt;

import br.com.michael_fausto.formulaSAE.controller.PilotController;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.BrakeMqttPublisher;
import br.com.michael_fausto.formulaSAE.mqtt.car.cooling.CoolingMqttPublisher;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MqttSchedule {

    private final BrakeMqttPublisher publisherBrake;
    private final CoolingMqttPublisher publisherCooling;

    @Scheduled(fixedRate = 1000)
    public void autoPublish() {
        publisherCooling.publishMockData();
        publisherBrake.publishMockData();
    }
}
