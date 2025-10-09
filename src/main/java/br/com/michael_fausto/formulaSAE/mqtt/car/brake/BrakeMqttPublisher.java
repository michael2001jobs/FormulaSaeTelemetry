package br.com.michael_fausto.formulaSAE.mqtt.car.brake;

import br.com.michael_fausto.formulaSAE.model.car.brakes.BrakeSensorData;
import br.com.michael_fausto.formulaSAE.model.mocks.BrakeMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Component
public class BrakeMqttPublisher {

    private static final Logger log = LoggerFactory.getLogger(BrakeMqttPublisher.class);
    private static final String TOPIC_BRAKES = "telemetry/brake";

    private final MqttClient client;
    private final ObjectMapper objectMapper;

    public void publishMockData() {
        try {
            BrakeSensorData brakeSensorData = BrakeMock.brakeMockSimulationArduino();
            String payload = objectMapper.writeValueAsString(brakeSensorData);

            MqttMessage message = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
            message.setQos(1);
            client.publish(TOPIC_BRAKES, message);

            log.info("Mock data published: {}", payload);

        } catch (JsonProcessingException e) {
            log.error("Error serializing mock to JSON", e);
        } catch (MqttException e) {
            log.error("Error publishing MQTT message", e);
        }
    }
}
