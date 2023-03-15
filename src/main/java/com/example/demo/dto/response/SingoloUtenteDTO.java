package com.example.demo.dto.response;

import com.example.demo.model.Post;
import com.example.demo.model.Utente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SingoloUtenteDTO {
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String pathImg;
    private long idClasse;
    private String nomeClasse;
    private String codiceClasse;

    private List<SingoloPostDTO> postPubblicati;

    public SingoloUtenteDTO(Utente u, List<Post> postPubblicati){
        id=u.getId();
        nome=u.getNome();
        cognome=u.getCognome();
        email=u.getEmail();
        username=u.getUsername();
        pathImg=u.getPathImg();
        if(u.getClasse()!=null) {
            idClasse = u.getClasse().getId();
            nomeClasse = u.getClasse().getNome();
            codiceClasse=u.getClasse().getCodice();
        }
        this.postPubblicati=postPubblicati==null?new ArrayList<>():postPubblicati.stream().map(SingoloPostDTO::new).toList();

    }
}
