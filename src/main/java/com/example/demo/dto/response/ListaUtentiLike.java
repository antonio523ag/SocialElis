package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "lista utenti che hanno messo like", description = "l'elenco degli utenti (username e id) che hanno messo like al post")
public class ListaUtentiLike {
    private List<UtenteLike> like;


    public ListaUtentiLike(List<Utente> utenti){
        like=utenti.stream().map(UtenteLike::new).toList();
    }


}
