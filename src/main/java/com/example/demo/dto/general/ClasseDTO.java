package com.example.demo.dto.general;

import com.example.demo.model.Classe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClasseDTO {
    private long id;
    private String codice;
    private String nome;
    private LocalDateTime dataInserimento;
    private LocalDate dataInizio;
    private String linkEsterno;

    public ClasseDTO(Classe c){
        id=c.getId();
        codice=c.getCodice();
        nome=c.getNome();
        dataInserimento=c.getDataInserimento().toLocalDateTime();
        dataInizio=c.getDataInizio();
        linkEsterno=c.getLinkEsterno();
    }
}
