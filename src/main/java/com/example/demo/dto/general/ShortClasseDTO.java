package com.example.demo.dto.general;

import com.example.demo.model.Classe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(name = "Classe Short", description = "classe con solo nome e codice")
public class ShortClasseDTO {
    private String nome;
    private String codice;

    public ShortClasseDTO(Classe c){
        nome=c.getNome();
        codice=c.getCodice();
    }
}
