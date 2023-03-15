package com.example.demo.dto.response;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@Schema(name = "elenco di post", description = "elenco di post da visualizzare, oltre ai post (che sono paginati) viene inviato il numero di pagina (parte da 0) e le pagine totali")
public class VisualizzaPostDTO {
    private List<PostDTO> post;
    private int numeroPagina;
    private int maxPagine;


    public VisualizzaPostDTO(List<PostDTO> post, int pageNumber,int maxPagine){
        this.post=post;
        numeroPagina=pageNumber;
        this.maxPagine=maxPagine;
    }
}
