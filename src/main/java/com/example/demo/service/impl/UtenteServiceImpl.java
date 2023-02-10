package com.example.demo.service.impl;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.model.Utente;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.service.UtenteService;
import org.springframework.stereotype.Service;

@Service
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;

    public UtenteServiceImpl(UtenteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Utente login(LoginRequest request) {
        return repo.findByEmailAndPassword(request.getEmail(),request.getPassword()).orElse(null);
    }
}
