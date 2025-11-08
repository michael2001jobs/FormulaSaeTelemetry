package br.com.michael_fausto.formulaSAE.controller.car;

import br.com.michael_fausto.formulaSAE.model.car.CarDTO;
import br.com.michael_fausto.formulaSAE.model.car.SetupCarDTO;
import br.com.michael_fausto.formulaSAE.service.car.SetupCarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setup_car")
@AllArgsConstructor
public class SetupCarController {

    private final SetupCarService service;

    @GetMapping("/get_setup")
    public ResponseEntity<SetupCarDTO> getSetup(String profileName) {
        SetupCarDTO dto = service.findByName(profileName);
        return ResponseEntity.ok(dto);
    }

}
