package com.example.demo.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "request - nuovo post", description = "testo per un nuovo post da inserire")
public class NuovoPostRequest {
    private String testo;
}
