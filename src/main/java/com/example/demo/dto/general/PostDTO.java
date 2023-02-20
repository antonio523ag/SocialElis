package com.example.demo.dto.general;

import com.example.demo.model.Post;
import com.example.demo.model.Utente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private long id;
    private String testo;
    private LocalDateTime dataInserimento;
    private String urlImmagine;
    private long idCreatore;
    private String usernameCreatore;
    private List<CommentoDTO> commenti;
    private int numeroDiLike;

    public PostDTO(Post p){
        id=p.getId();
        testo=p.getTesto();
        dataInserimento=p.getDataInserimento().toLocalDateTime();
        urlImmagine=p.getUrlImmagine();
        idCreatore=p.getCreatore().getId();
        usernameCreatore=p.getCreatore().getUsername();
        commenti=p.getCommenti()==null?new ArrayList<>():p.getCommenti().stream().map(CommentoDTO::new).toList();
        numeroDiLike=p.getMiPiace()==null?0:p.getMiPiace().size();
    }
}
