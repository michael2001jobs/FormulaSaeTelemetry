package br.com.michael_fausto.formulaSAE.mqtt;

import br.com.michael_fausto.formulaSAE.mqtt.car.brake.back.left.BrakeMqttPublisherBL;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.back.right.BrakeMqttPublisherBR;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.back.right.BrakeMqttSubscriberBR;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.front.left.BrakeMqttPublisherFL;
import br.com.michael_fausto.formulaSAE.mqtt.car.brake.front.right.BrakeMqttPublisherFR;
import br.com.michael_fausto.formulaSAE.mqtt.car.cooling.CoolingMqttPublisher;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class MqttSchedule {

    private final CoolingMqttPublisher publisherCooling;

    private final BrakeMqttPublisherFL publisherBrakeFL;
    private final BrakeMqttPublisherFR publisherBrakeFR;
    private final BrakeMqttPublisherBL publisherBrakeBL;
    private final BrakeMqttPublisherBR publisherBrakeBR;

    //@Scheduled(fixedRate = 5000)
    public void autoPublish() {
        publisherCooling.publishMockData();
    }

    //@Scheduled(fixedRate = 5000)
    public void autoPublishBrake() {
        publisherBrakeFL.publishMockData();
        publisherBrakeFR.publishMockData();
        publisherBrakeBL.publishMockData();
        publisherBrakeBR.publishMockData();
    }

}
