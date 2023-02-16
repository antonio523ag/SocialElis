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

    public VisualizzaPostDTO(List<Post> post){
        this.post=post.stream().map(PostDTO::new).toList();
    }
}
