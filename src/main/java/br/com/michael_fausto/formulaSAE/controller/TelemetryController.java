package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.entity.TelemetryEntity;
import br.com.michael_fausto.formulaSAE.mapper.PilotMapper;
import br.com.michael_fausto.formulaSAE.model.dto.PilotDTO;
import br.com.michael_fausto.formulaSAE.repository.TelemetryRepository;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import br.com.michael_fausto.formulaSAE.service.TelemetryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pilot")
public class TelemetryController {

    private final TelemetryService service;
    private final PilotService pilotService;
    private final TelemetryRepository repository;
    private final PilotMapper mapper;

    @PostMapping("/{pilotId}/telemetry/create")
    public ResponseEntity<?> createTelemetry(@PathVariable Long pilotId) {
        service.saveList(service.buildTelemetryList(pilotService.getPilotEntity(pilotId)));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{pilotId}/telemetry")
    public ResponseEntity<List<TelemetryEntity>> getAll(@PathVariable Long pilotId) {
        service.getAllTelemetry(pilotId);
        return ResponseEntity.ok(service.getAllTelemetry(pilotId));
    }

}


