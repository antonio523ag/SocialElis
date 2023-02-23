package com.example.demo.service;

import com.example.demo.dto.request.CommentaPostRequest;
import com.example.demo.dto.request.IdCommentoRequest;
import com.example.demo.model.Utente;

public interface CommentoService {
    void commentaPost(CommentaPostRequest request, Utente u);

    void cancellaPost(IdCommentoRequest request, Utente u);
}
