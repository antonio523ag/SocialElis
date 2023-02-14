package com.example.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreaClasseDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;
    @NotNull
    private LocalDate dataInizio;

    private String linkEsterno;

}
