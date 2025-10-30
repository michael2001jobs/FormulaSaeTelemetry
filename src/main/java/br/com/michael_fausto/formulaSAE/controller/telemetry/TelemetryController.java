package br.com.michael_fausto.formulaSAE.controller.telemetry;

import br.com.michael_fausto.formulaSAE.model.telemetry.TelemetryDTO;
import br.com.michael_fausto.formulaSAE.service.users.UserService;
import br.com.michael_fausto.formulaSAE.service.telemetry.TelemetryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

//    @GetMapping("/create")
//    public ResponseEntity<TelemetryDTO> showTelemetry(@AuthenticationPrincipal UserDetails user, String name, ) {
//        TelemetryDTO telemetryDTO = telemetryService.entityToDTO(telemetryService.findById(telemetryId));
//        return ResponseEntity.ok(telemetryDTO);
//    }
//
//    @DeleteMapping("/telemetry/{telemetryId}")
//    public ResponseEntity<TelemetryDTO> deleteTelemetry(@PathVariable Long telemetryId) {
//        TelemetryDTO telemetryDTO =telemetryService.entityToDTO(telemetryService.findById(telemetryId));
//        return ResponseEntity.ok(telemetryDTO);
//    }
}


