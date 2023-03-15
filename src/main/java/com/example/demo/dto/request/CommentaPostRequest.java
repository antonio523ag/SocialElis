package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - CommentaPost", description = "inserire il commento e l'id del post da commentare")
public class CommentaPostRequest {
    private String commento;
    private long idPost;
}
