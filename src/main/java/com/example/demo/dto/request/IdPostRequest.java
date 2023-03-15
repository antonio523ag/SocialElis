package com.example.demo.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - idPost", description = "commento per la cancellazione di un determinato post")
public class IdPostRequest {
    private long id;
}
