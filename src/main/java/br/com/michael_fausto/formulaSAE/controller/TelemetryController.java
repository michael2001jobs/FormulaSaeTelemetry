package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.model.telemetry.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import br.com.michael_fausto.formulaSAE.service.TelemetryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/pilot")
public class TelemetryController {

    private final TelemetryService telemetryService;
    private final PilotService pilotService;

//    @PostMapping("/telemetry/{pilotId}")
//    public ResponseEntity<?> createTelemetry(@PathVariable Long pilotId) {
//        telemetryService.saveList(telemetryService.buildTelemetryList(pilotService.findById(pilotId)));
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/telemetry/{telemetryId}")
    public ResponseEntity<TelemetryDTO> showTelemetry(@PathVariable Long telemetryId) {
        TelemetryDTO telemetryDTO = telemetryService.entityToDTO(telemetryService.findById(telemetryId));
        return ResponseEntity.ok(telemetryDTO);
    }

    @DeleteMapping("/telemetry/{telemetryId}")
    public ResponseEntity<TelemetryDTO> deleteTelemetry(@PathVariable Long telemetryId) {
        TelemetryDTO telemetryDTO =telemetryService.entityToDTO(telemetryService.findById(telemetryId));
        return ResponseEntity.ok(telemetryDTO);
    }
}


