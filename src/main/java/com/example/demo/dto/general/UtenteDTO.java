package com.example.demo.dto.general;

import com.example.demo.model.Ruolo;
import com.example.demo.model.Utente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Utente", description = "utente per le response")
public class UtenteDTO {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String pathImg;
    private Ruolo ruolo;
    private long idClasse;

    public UtenteDTO(Utente u){
        id=u.getId();
        nome=u.getNome();
        cognome=u.getCognome();
        email=u.getEmail();
        username=u.getUsername();
        pathImg=u.getPathImg();
        ruolo=u.getRuolo();
        idClasse=u.getClasse()==null?-1:u.getClasse().getId();


    }
}
