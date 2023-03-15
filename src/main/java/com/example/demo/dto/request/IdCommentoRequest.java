package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - IdCommento", description = "id per la cancellazione di un determinato commento")
public class IdCommentoRequest {
    private long id;
}
