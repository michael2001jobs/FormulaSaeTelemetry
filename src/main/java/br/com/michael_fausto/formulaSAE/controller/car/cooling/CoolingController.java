package br.com.michael_fausto.formulaSAE.controller.car.cooling;

import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import br.com.michael_fausto.formulaSAE.service.car.cooling.CoolingSetupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cooling-setup")
public class CoolingController {

    private final CoolingSetupService service;

    @PostMapping("/create")
    public ResponseEntity<CoolingSetupDTO> create(@RequestBody CoolingSetupDTO dto) {
        service.create(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CoolingSetupDTO>> getAll() {
        List<CoolingSetupDTO> coolingSetupDTOList = service.showAll();
        return ResponseEntity.ok(coolingSetupDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoolingSetupDTO> getById(@PathVariable Long id) {
        CoolingSetupDTO responseDTO = service.findById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CoolingSetupDTO> deleteById(@PathVariable Long id) {
        CoolingSetupDTO responseDTO = service.deleteById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<CoolingSetupDTO> upadateName(@PathVariable Long id, @RequestBody String updateName) {
        CoolingSetupDTO responseDTO = service.updateName(updateName, id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/copy/{id}")
    public ResponseEntity<CoolingSetupDTO> copyAndEditSetup(@PathVariable Long id) {
        CoolingSetupDTO responseDTO = service.copyAndEdit(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/teste")
    public ResponseEntity<Boolean> teste() {
        return ResponseEntity.ok(service.isEmpty());
    }

}
