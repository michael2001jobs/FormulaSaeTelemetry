package br.com.michael_fausto.formulaSAE.model.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsersRegisterDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Email
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@pucpr\\.com$", message = "Incorrect email or with the wrong extension, should be @pucpr.com")
    private String email;

    private String password;

}