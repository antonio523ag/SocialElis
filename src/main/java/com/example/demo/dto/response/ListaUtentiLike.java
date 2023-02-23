package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListaUtentiLike {
    private List<UtenteLike> like;


    public ListaUtentiLike(List<Utente> utenti){
        like=utenti.stream().map(UtenteLike::new).toList();
    }


}
