package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - modifica utente", description = "request per modificare username e/o password, per entrambe le modifiche deve essere inserita la vecchia password")
public class ModificaUtenteRequest {
    private String username;
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String password;
    private String nuovaPassword;
    private String passwordRipetuta;
}
