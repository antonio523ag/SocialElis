package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - idUtente", description = "id utente per varie query, se l'elenco della risposta ammette più valori va valorizzata la page")
public class IdUtenteRequest {
    @Min(1)
    private long id;
    @Min(0)
    private int page;


}
