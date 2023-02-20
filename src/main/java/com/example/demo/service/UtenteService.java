package com.example.demo.service;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.*;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.model.Utente;
import org.springframework.web.multipart.MultipartFile;

public interface UtenteService {

    Utente login(LoginRequest request);
    Utente getUtenteById(IdUtenteRequest request);
    void registrati(RegistrazioneRequest request);
    void salvaUtente(Utente u);
    VisualizzaRichiesteResponse visualizzaRichieste();
    void accettaRichieste(IdUtenteBulkRequest request);
    void accettaRichiesta(IdUtenteRequest request);
    UtenteDTO modificaUtente(ModificaUtenteRequest request);
    UtenteDTO aggiungiImmagineProfilo(Utente u, MultipartFile img);
    UtenteDTOListResponse cercaUtente(CercaUtenteRequest request, Utente richiedente);
}
