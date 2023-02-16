package com.example.demo.service;

import com.example.demo.dto.request.IdUtenteBulkRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.dto.response.RichiestaResponse;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.model.Utente;

import java.util.List;

public interface UtenteService {

    Utente login(LoginRequest request);
    void registrati(RegistrazioneRequest request);
    void salvaUtente(Utente u);

    VisualizzaRichiesteResponse visualizzaRichieste();
    void accettaRichieste(IdUtenteBulkRequest request);

    void accettaRichiesta(IdUtenteRequest request);
}
