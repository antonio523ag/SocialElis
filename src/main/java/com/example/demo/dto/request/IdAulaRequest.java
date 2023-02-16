package com.example.demo.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdAulaRequest {
    @Min(1)
    private long idAula;
}
