package br.com.michael_fausto.formulaSAE.controller.users;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.model.users.UsersDTO;
import br.com.michael_fausto.formulaSAE.service.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/pilot")
public class UserController {

    private final UserService userService;


    @GetMapping("/{id}")  //ok
    public ResponseEntity<UsersDTO> getPilot(@PathVariable Long id) {
        UsersEntity entity = userService.findById(id);
        UsersDTO dto = userService.convertDto(entity);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}") //ok
    public ResponseEntity<UsersDTO> deletePilot(@PathVariable Long id) {
        UsersEntity entity = userService.findById(id);
        UsersDTO dto = userService.convertDto(entity);
        userService.deletePilot(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}") //ok
    public ResponseEntity<UsersDTO> updatePilot(@PathVariable Long id, @Validated @RequestBody UsersDTO dto) {
        UsersEntity entity = userService.findById(id);
        userService.updatePilot(dto, id);
        return ResponseEntity.ok(dto);
    }
}
