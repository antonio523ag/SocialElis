package com.example.demo.dto.response;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.model.Utente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "elenco di utenti", description = "elenco di utenti per vari endpoint")
public class UtenteDTOListResponse {
    private List<UtenteDTO> utentiTrovati;

    public UtenteDTOListResponse(List<Utente> utenti){
        utentiTrovati=utenti.stream().map(UtenteDTO::new).toList();
    }
}
