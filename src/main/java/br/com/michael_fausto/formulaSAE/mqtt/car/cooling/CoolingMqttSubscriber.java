package br.com.michael_fausto.formulaSAE.mqtt.car.cooling;

import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingSensorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class CoolingMqttSubscriber {

    private CoolingSensorData coolingSensorData;

    private static final Logger log = LoggerFactory.getLogger(CoolingMqttSubscriber.class);
    private static final String TOPIC_COOLING = "telemetry/cooling";

    private final MqttClient client;
    private final ObjectMapper objectMapper;

    public CoolingMqttSubscriber(MqttClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void subscribe() {
        try {
            client.subscribe(TOPIC_COOLING, (topic, message) -> handleMessage(topic, message));
            log.info("Subscribed to {}", TOPIC_COOLING);
        } catch (MqttException e) {
            log.error("Error subscribing to MQTT topic", e);
        }
    }

    private void handleMessage(String topic, MqttMessage message) {
        try {
            String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
            log.info("Received telemetry on [{}]: {}", topic, payload);

            coolingSensorData = objectMapper.readValue(payload, CoolingSensorData.class);

            log.debug("BrakeData parsed: {}", coolingSensorData);

        } catch (Exception e) {
            log.error("Error processing MQTT message", e);
        }
    }

    public CoolingSensorData getLast() {
        return new CoolingSensorData(
                coolingSensorData.coolingSystemTemperature(),
                coolingSensorData.reservoirVolume(),
                coolingSensorData.fan());
    }
}
