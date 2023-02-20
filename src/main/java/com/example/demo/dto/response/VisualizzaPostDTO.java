package com.example.demo.dto.response;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
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
