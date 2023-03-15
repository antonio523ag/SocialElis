package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "richiesta di iscrizione", description = "elenco delle richieste di iscrizione aperte presenti nel sistema")
public class VisualizzaRichiesteResponse {
    private List<SingolaRichiestaResponse> richieste;

    public VisualizzaRichiesteResponse(List<Utente> utenti){
        richieste=utenti.stream().map(SingolaRichiestaResponse::new).sorted((a, b)->a.getNomeAula().compareToIgnoreCase(b.getNomeAula())).toList();
    }
}
