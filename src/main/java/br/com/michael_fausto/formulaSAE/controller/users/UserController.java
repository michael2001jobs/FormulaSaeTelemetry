package br.com.michael_fausto.formulaSAE.controller.users;

import br.com.michael_fausto.formulaSAE.model.users.UsersDTO;
import br.com.michael_fausto.formulaSAE.model.auth.UsersRegisterDTO;
import br.com.michael_fausto.formulaSAE.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/view")  //ok
    public ResponseEntity<UsersDTO> getPilot(@AuthenticationPrincipal UserDetails user) {
        UsersDTO response = userService.findByEmail(user.getUsername());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<UsersDTO> deletePilot(@AuthenticationPrincipal UserDetails user) {
        UsersDTO response = userService.deletePilot(user.getUsername());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update") //ok
    public ResponseEntity<UsersDTO> updatePilot(@AuthenticationPrincipal UserDetails user,
                                                @Validated @RequestBody UsersRegisterDTO dto) {
        UsersDTO updateUser = userService.updatePilot(dto, user.getUsername());
        return ResponseEntity.ok(updateUser);
    }
}
