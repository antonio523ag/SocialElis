package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "richiesta di iscrizione", description = "richiesta di iscrizione presente nel sistema, viene inviato nome cognome ed email con il nome aula per verificare che l'utente esista e l'id per accettarlo")
public class SingolaRichiestaResponse {
    private long idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String nomeAula;

    public SingolaRichiestaResponse(Utente u){
        idUtente=u.getId();
        nome=u.getNome();
        cognome=u.getCognome();
        email=u.getEmail();
        nomeAula=u.getClasse().getNome();
    }
}
