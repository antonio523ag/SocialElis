package com.example.demo.service;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.IdPostRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.NumeroPaginaRequest;
import com.example.demo.dto.request.NuovoPostRequest;
import com.example.demo.dto.response.VisualizzaPostDTO;
import com.example.demo.model.Post;
import com.example.demo.model.Utente;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    List<Post> findAllByIdClasse(Utente u);
    List<Post> findAllByIdCreatore(IdUtenteRequest request);

    PostDTO salvaPost(String request, Utente u);

    PostDTO salvaPost(String request, MultipartFile file, Utente u);

    VisualizzaPostDTO visualizzaPostPerUtente(IdUtenteRequest request, Utente u);

    VisualizzaPostDTO visualizzaPostHome(NumeroPaginaRequest r, Utente u);

    void cancellaPost(IdPostRequest request, Utente u);
}
