package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.Pilot;
import br.com.michael_fausto.formulaSAE.service.PilotService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PilotController {

    PilotService service;

    public PilotController(PilotService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PilotEntity> getPilotNoPassword(@PathVariable Long id) {
        PilotEntity pilot = service.getPilot(id);
        return ResponseEntity.ok(pilot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePilot(@PathVariable Long id) {
        PilotEntity entity = service.getPilot(id);
        service.deletePilot(entity);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Pilot> createPilot(@Valid @RequestBody Pilot pilot) {
        service.createPilot(pilot);
        return ResponseEntity.ok(pilot);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePilot(@PathVariable Long id, @Valid @RequestBody Pilot pilot) {
        service.updatePilot(id, pilot);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/so1") //Gambiarra
    public List<Pilot> teste(@RequestBody List<Pilot> list) {
        for (int i = 0; i < list.size(); i++) {
            service.createPilot(list.get(i));
        }
        return list;
    }
}
