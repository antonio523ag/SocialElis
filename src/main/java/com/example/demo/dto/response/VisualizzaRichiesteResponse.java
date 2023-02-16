package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class VisualizzaRichiesteResponse {
    private List<RichiestaResponse> richieste;

    public VisualizzaRichiesteResponse(List<Utente> utenti){
        richieste=utenti.stream().map(RichiestaResponse::new).sorted((a,b)->a.getNomeAula().compareToIgnoreCase(b.getNomeAula())).toList();
    }
}
