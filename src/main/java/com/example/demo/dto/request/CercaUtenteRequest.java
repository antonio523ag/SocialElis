package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - cercaUtente", description = "il testo della ricerca non può essere vuoto")
public class CercaUtenteRequest {
    @NotBlank
    private String testoRicerca;
    private int numeroDiPagina;
}
