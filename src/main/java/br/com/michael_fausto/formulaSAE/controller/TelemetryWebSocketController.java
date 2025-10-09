package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.config.WebSocketServerConfig;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import br.com.michael_fausto.formulaSAE.service.TelemetryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/telemetry")
public class TelemetryWebSocketController {

    private final TelemetryService telemetryService;
    private final PilotService pilotService;
    private final WebSocketServerConfig server = new WebSocketServerConfig(8081);



    @PostMapping("/close")
    public ResponseEntity<?> closeTelemetry() throws InterruptedException {
        server.stop();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/on")
    public ResponseEntity<String> onTelemetry() {
        server.start();
        return ResponseEntity.ok("ServerOn");
    }

    //@Scheduled(fixedRate = 1000)
//    public void openTelemetry() {
//        if (server.isRunning()) {
//            String message = String.valueOf(telemetryService.brakeMqttReading(telemetryService.findById(1L)));
//            server.broadcastMessage(message);
//        }
//    }
}
