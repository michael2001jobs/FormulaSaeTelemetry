package br.com.michael_fausto.formulaSAE.controller.car;

import br.com.michael_fausto.formulaSAE.entity.car.CoolingTelemetryEntity;
import br.com.michael_fausto.formulaSAE.model.mocks.CoolingMock;
import br.com.michael_fausto.formulaSAE.service.car.CoolingService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoolingInputDataController {

    private final CoolingService service;
    private final SimpMessagingTemplate messagingTemplate;

    public CoolingInputDataController(CoolingService service, SimpMessagingTemplate messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    //@Scheduled(fixedRate = 200)
    public void sendCoolingTelemetry() {
        CoolingTelemetryEntity status = service.setCoolingEntity(
                service.coolingSensorParse(CoolingMock.coolingMockSimulatadArduino())
        );
        service.saveCoolingTelemetryEntity(status);
        messagingTemplate.convertAndSend("/topic/cooling", status);
    }
}
