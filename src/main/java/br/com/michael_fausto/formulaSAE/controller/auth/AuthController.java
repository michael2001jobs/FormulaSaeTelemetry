package br.com.michael_fausto.formulaSAE.controller.auth;

import br.com.michael_fausto.formulaSAE.entity.users.UsersEntity;
import br.com.michael_fausto.formulaSAE.model.auth.LoginResponseDTO;
import br.com.michael_fausto.formulaSAE.model.auth.UsersLoginDTO;
import br.com.michael_fausto.formulaSAE.model.auth.UsersRegisterDTO;
import br.com.michael_fausto.formulaSAE.repository.users.UsersRepository;
import br.com.michael_fausto.formulaSAE.service.auth.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsersRepository repository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsersLoginDTO login) {
        var userPassword = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        var auth = this.authenticationManager.authenticate(userPassword);

        var token = tokenService.generateToken((UsersEntity) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsersRegisterDTO register) {
        if(this.repository.findByEmail(register.getEmail()) != null) return ResponseEntity.badRequest().build();

        String cripPassword = new BCryptPasswordEncoder().encode(register.getPassword());
        UsersEntity newUser = new UsersEntity(
                register.getName(),
                register.getEmail(),
                cripPassword);

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/teste")
    public String teste() {
        return "TOKEN OK";
    }
}
