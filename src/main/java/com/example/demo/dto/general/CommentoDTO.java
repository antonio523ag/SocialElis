package com.example.demo.dto.general;

import com.example.demo.model.Commento;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Schema(name = "Commento", description = "commento per le response")
public class CommentoDTO {
    private String testo;
    private LocalDateTime dataInserimento;
    private long idUtente;
    private String usernameCreatore;

    public CommentoDTO(Commento c){
        testo=c.getTesto();
        dataInserimento=c.getDataInserimento().toLocalDateTime();
        idUtente=c.getUtente().getId();
        usernameCreatore=c.getUtente().getUsername();
    }
}
