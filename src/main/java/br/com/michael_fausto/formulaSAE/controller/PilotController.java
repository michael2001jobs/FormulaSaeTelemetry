package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.model.dto.PilotDTO;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import br.com.michael_fausto.formulaSAE.service.TelemetryService;
import br.com.michael_fausto.formulaSAE.service.car.BrakeService;
import br.com.michael_fausto.formulaSAE.service.car.CoolingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/pilot")
public class PilotController {

    private final PilotService pilotService;
    private final TelemetryService telemetryService;
    private final BrakeService brakeService;
    private final CoolingService coolingService;

    @PostMapping("/create")  //ok
    public ResponseEntity<PilotDTO> createPilot(@Valid @RequestBody PilotDTO pilotDTO) {
        pilotService.savePilot(pilotService.buildPilot(pilotDTO));
        return ResponseEntity.ok(pilotDTO);
    }

    @GetMapping("/{id}")  //ok
    public ResponseEntity<PilotDTO> getPilot(@PathVariable Long id) {
        return ResponseEntity.ok(pilotService.getPilotDTO(id));
    }

    @DeleteMapping("/{id}") //ok
    public ResponseEntity<PilotDTO> deletePilot(@PathVariable Long id) {
        return ResponseEntity.ok(pilotService.deletePilot(id));
    }

    @PutMapping("/{id}/update") //ok
    public ResponseEntity<PilotDTO> updatePilot(@PathVariable Long id, @Validated @RequestBody PilotDTO pilotDTO) {
        return ResponseEntity.ok(pilotService.updatePilot(id, pilotDTO));
    }

    //@Scheduled(fixedRate = 1000)
    public void testeTeste() {
        TelemetryEntity entity = telemetryService.findById(1L);
        entity.getBrakeTelemetry().add(brakeService.mqttBrake());
        entity.getCoolingTelemetry().add(coolingService.mqttCooling());
    }
}
