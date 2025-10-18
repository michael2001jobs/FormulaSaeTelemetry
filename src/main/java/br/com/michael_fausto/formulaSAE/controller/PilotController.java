package br.com.michael_fausto.formulaSAE.controller;

import br.com.michael_fausto.formulaSAE.entity.PilotEntity;
import br.com.michael_fausto.formulaSAE.model.pilot.PilotDTO;
import br.com.michael_fausto.formulaSAE.service.PilotService;
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

    @PostMapping("/register")  //ok
    public ResponseEntity<PilotDTO> createPilot(@Valid @RequestBody PilotDTO dto) {
        PilotEntity entity = pilotService.convertEntity(dto);
        pilotService.savePilot(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")  //ok
    public ResponseEntity<PilotDTO> getPilot(@PathVariable Long id) {
        PilotEntity entity = pilotService.findById(id);
        PilotDTO dto = pilotService.convertDto(entity);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}") //ok
    public ResponseEntity<PilotDTO> deletePilot(@PathVariable Long id) {
        PilotEntity entity = pilotService.findById(id);
        PilotDTO dto = pilotService.convertDto(entity);
        pilotService.deletePilot(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}") //ok
    public ResponseEntity<PilotDTO> updatePilot(@PathVariable Long id, @Validated @RequestBody PilotDTO dto) {
        PilotEntity entity = pilotService.findById(id);
        pilotService.updatePilot(dto, id);
        return ResponseEntity.ok(dto);
    }


}
