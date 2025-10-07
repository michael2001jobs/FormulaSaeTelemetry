package br.com.michael_fausto.formulaSAE.mqtt.car.brake;

import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;


@Component
public class BrakeMqttSubscriber {

    private BrakeSensorData brakeSensorData;

    private static final Logger log = LoggerFactory.getLogger(BrakeMqttSubscriber.class);
    private static final String TOPIC_BRAKES = "telemetry/brake";

    private final MqttClient client;
    private final ObjectMapper objectMapper;

    public BrakeMqttSubscriber(MqttClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void subscribe() {
        try {
            client.subscribe(TOPIC_BRAKES, (topic, message) -> handleMessage(topic, message));
            log.info("Subscribed to {}", TOPIC_BRAKES);
        } catch (MqttException e) {
            log.error("Error subscribing to MQTT topic", e);
        }
    }

    private void handleMessage(String topic, MqttMessage message) {
        try {
            String payload = new String(message.getPayload(), StandardCharsets.UTF_8);
            log.info("Received telemetry on [{}]: {}", topic, payload);

            brakeSensorData = objectMapper.readValue(payload, BrakeSensorData.class);

            log.debug("BrakeData parsed: {}", brakeSensorData);

        } catch (Exception e) {
            log.error("Error processing MQTT message", e);
        }
    }

    public BrakeSensorData getLast() {
        return new BrakeSensorData(brakeSensorData.discBreakTemperature(), brakeSensorData.brakeFluidPressure(), brakeSensorData.handBrake());
    }
}
