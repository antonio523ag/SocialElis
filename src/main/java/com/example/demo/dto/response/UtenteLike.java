package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtenteLike {

    private String username;
    private long id;

    public UtenteLike(Utente u){
        username=u.getUsername();
        id=u.getId();
    }

}
