package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RichiestaResponse {
    private long idUtente;
    private String nome;
    private String cognome;
    private String email;
    private String nomeAula;

    public RichiestaResponse(Utente u){
        idUtente=u.getId();
        nome=u.getNome();
        cognome=u.getCognome();
        email=u.getEmail();
        nomeAula=u.getClasse().getNome();
    }
}
