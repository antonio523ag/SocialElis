package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentaPostRequest {
    private String commento;
    private long idPost;
}
