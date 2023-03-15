package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - numeroDiPagina", description = "per i metodi dove non ci sono campi di filtro ma molti dati da ritornare")
public class NumeroPaginaRequest {

    private int numeroPagina;
}
