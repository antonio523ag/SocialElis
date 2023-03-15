package com.example.demo.dto.response;

import com.example.demo.dto.general.CommentoDTO;
import com.example.demo.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingoloPostDTO {
    private long id;
    private String testo;
    private LocalDateTime dataInserimento;
    private String urlImmagine;
    private List<CommentoDTO> commenti;
    private int numeroDiLike;

    public SingoloPostDTO(Post p){
        id=p.getId();
        testo=p.getTesto();
        dataInserimento=p.getDataInserimento().toLocalDateTime();
        urlImmagine=p.getUrlImmagine();
        commenti=p.getCommenti()==null?new ArrayList<>():p.getCommenti().stream().map(CommentoDTO::new).toList();
        numeroDiLike=p.getMiPiace()==null?0:p.getMiPiace().size();
    }
}
