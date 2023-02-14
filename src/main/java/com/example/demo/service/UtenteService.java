package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.model.Utente;

public interface UtenteService {

    Utente login(LoginRequest request);
    void registrati(RegistrazioneRequest request);
    void salvaUtente(Utente u);

}
