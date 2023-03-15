package com.example.demo.dto.response;

import com.example.demo.model.Utente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "like", description = "singolo like messo ad un post")
public class UtenteLike {

    private String username;
    private long id;

    public UtenteLike(Utente u){
        username=u.getUsername();
        id=u.getId();
    }

}
