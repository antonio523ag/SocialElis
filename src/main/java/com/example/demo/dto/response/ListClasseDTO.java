package com.example.demo.dto.response;

import com.example.demo.dto.general.ShortClasseDTO;
import com.example.demo.model.Classe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "lista classi aperte", description = "elenco delle classi aperte fatto solo da nome e codiceAula")
public class ListClasseDTO {
    private List<ShortClasseDTO> classiAperte;

    public ListClasseDTO(List<Classe> classiAperte){
        this.classiAperte =classiAperte==null?null:classiAperte.stream().map(ShortClasseDTO::new).toList();
    }

}
