package br.com.michael_fausto.formulaSAE.controller.auth;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.model.auth.LoginResponseDTO;
import br.com.michael_fausto.formulaSAE.model.auth.UsersLoginDTO;
import br.com.michael_fausto.formulaSAE.model.auth.UsersRegisterDTO;
import br.com.michael_fausto.formulaSAE.service.auth.AuthService;
import br.com.michael_fausto.formulaSAE.service.auth.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid UsersLoginDTO login) {
        var userPassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((UsersEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsersRegisterDTO register) {
        authService.newUser(register);
        return ResponseEntity.ok().build();
    }

}
