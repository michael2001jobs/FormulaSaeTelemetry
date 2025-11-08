package br.com.michael_fausto.formulaSAE.controller.car.brake;

import br.com.michael_fausto.formulaSAE.model.car.brakes.dto.BrakeSetupDTO;
import br.com.michael_fausto.formulaSAE.service.car.brake.BrakeSetupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brake-setup")
@AllArgsConstructor
public class BrakeSetupController {

    private final BrakeSetupService service;

    @PostMapping("/create")
    public ResponseEntity<BrakeSetupDTO> create(@RequestBody BrakeSetupDTO dto) {
        service.create(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<BrakeSetupDTO>> getAll() {
        List<BrakeSetupDTO> listBrakeSetup = service.showAll();
        return ResponseEntity.ok(listBrakeSetup);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrakeSetupDTO> getById(@PathVariable Long id) {
        BrakeSetupDTO responseDTO = service.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BrakeSetupDTO> deleteById(@PathVariable Long id) {
        BrakeSetupDTO responseDTO = service.deleteById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<BrakeSetupDTO> updateName(@PathVariable Long id, @RequestBody String  updateName) {
        BrakeSetupDTO responseDTO = service.updateName(updateName, id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/copy/{id}")
    public ResponseEntity<BrakeSetupDTO> copyAndEditSetup(@PathVariable Long id) {
        BrakeSetupDTO responseDTO = service.copyAndEdit(id);
        return ResponseEntity.ok(responseDTO);
    }
}
