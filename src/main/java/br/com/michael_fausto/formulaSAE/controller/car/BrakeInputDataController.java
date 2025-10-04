package br.com.michael_fausto.formulaSAE.controller.car;

import br.com.michael_fausto.formulaSAE.entity.car.BrakeTelemetryEntity;
import br.com.michael_fausto.formulaSAE.model.mocks.BrakeMock;
import br.com.michael_fausto.formulaSAE.service.car.BrakeTelemetryService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class BrakeInputDataController {

    private final BrakeTelemetryService serviceBrake;
    private final SimpMessagingTemplate messagingTemplate;

    public BrakeInputDataController(BrakeTelemetryService serviceBrake, SimpMessagingTemplate messagingTemplate) {
        this.serviceBrake = serviceBrake;
        this.messagingTemplate = messagingTemplate;
    }

    //@Scheduled(fixedRate = 200000 )
    public void sendBrakeTelemetry() {
        BrakeTelemetryEntity status = serviceBrake.setBrakeTelemetryEntity(
                serviceBrake.brakeSensorParse(BrakeMock.brakeMockSimulatadArduino())
        );
        serviceBrake.saveBrakeTelemetry(status);
        messagingTemplate.convertAndSend("/topic/brake", status);
    }
}