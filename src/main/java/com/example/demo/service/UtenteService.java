package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.model.Utente;

public interface UtenteService {

    Utente login(LoginRequest request);
}
