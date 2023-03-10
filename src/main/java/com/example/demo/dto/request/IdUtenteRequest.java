package com.example.demo.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdUtenteRequest {
    @Min(1)
    private long id;
    @Min(0)
    private int page;


}
