package br.com.michael_fausto.formulaSAE.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Pilot {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@pucpr\\.com$", message = "Incorrect email or with the wrong extension, should be @pucpr.com")
    private String email;

    public Pilot(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Pilot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
