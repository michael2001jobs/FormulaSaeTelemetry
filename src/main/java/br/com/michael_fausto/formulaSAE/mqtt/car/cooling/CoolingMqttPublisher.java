package br.com.michael_fausto.formulaSAE.mqtt.car.cooling;

import br.com.michael_fausto.formulaSAE.model.car.cooling.CoolingSensorData;
import br.com.michael_fausto.formulaSAE.model.mocks.CoolingMock;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@AllArgsConstructor
@Component
public class CoolingMqttPublisher {

    private static final Logger log = LoggerFactory.getLogger(CoolingMqttPublisher.class);
    private static final String TOPIC_COOLING = "telemetry/cooling";

    private final MqttClient client;
    private final ObjectMapper objectMapper;

    public void publishMockData() {
        try {
            CoolingSensorData coolingSensorData = CoolingMock.coolingMockSimulatadArduino();
            String payload = objectMapper.writeValueAsString(coolingSensorData);

            MqttMessage message = new MqttMessage(payload.getBytes(StandardCharsets.UTF_8));
            message.setQos(1);
            client.publish(TOPIC_COOLING, message);

            log.info("Mock data published: {}", payload);

        } catch (JsonProcessingException e) {
            log.error("Error serializing mock to JSON", e);
        } catch (MqttException e) {
            log.error("Error publishing MQTT message", e);
        }
    }
}
