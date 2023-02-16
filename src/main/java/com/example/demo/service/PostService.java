package com.example.demo.service;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.NuovoPostRequest;
import com.example.demo.model.Post;
import com.example.demo.model.Utente;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<Post> findAllByIdClasse(Utente u);
    List<Post> findAllByIdCreatore(IdUtenteRequest request);
    PostDTO salvaPost(NuovoPostRequest request, MultipartFile file,Utente u);
}
