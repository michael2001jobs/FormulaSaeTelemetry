package br.com.michael_fausto.formulaSAE.controller.car;

import br.com.michael_fausto.formulaSAE.entity.car.cooling.CoolingSetupEntity;
import br.com.michael_fausto.formulaSAE.model.car.cooling.dto.CoolingSetupDTO;
import br.com.michael_fausto.formulaSAE.service.car.cooling.CoolingSetupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/cooling")
public class CoolingSetupController {

    private final CoolingSetupService setupService;

    @PostMapping("/create")
    public ResponseEntity<CoolingSetupDTO> createCoolingSetup(@RequestBody CoolingSetupDTO dto) {
        CoolingSetupEntity entity = setupService.buildCoolingSetup(dto);
        setupService.saveEntity(entity);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoolingSetupDTO> getCoolingSetup(@PathVariable Long id) {
        CoolingSetupEntity entity = setupService.findById(id);
        CoolingSetupDTO dto = setupService.convertDto(entity);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CoolingSetupDTO> deleteCoolingSetup(@PathVariable Long id) {
        CoolingSetupEntity entity = setupService.findById(id);
        CoolingSetupDTO dto = setupService.convertDto(entity);
        setupService.deleteCoolingSetup(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoolingSetupDTO> updateCoolingSetup(@PathVariable Long id, @RequestBody CoolingSetupDTO dto) {
        CoolingSetupEntity entity = setupService.findById(id);
        setupService.updateCoolingSetupEntity(dto, id);
        return ResponseEntity.ok(dto);
    }
}
