package com.example.demo.service.impl;

import com.example.demo.dto.request.IdUtenteBulkRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.exception.UtenteBloccatoException;
import com.example.demo.exception.UtenteNonTrovatoException;
import com.example.demo.model.Classe;
import com.example.demo.model.Utente;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.service.ClasseService;
import com.example.demo.service.UtenteService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;
    private final ClasseService classeService;

    public UtenteServiceImpl(UtenteRepository repo,ClasseService classeService) {
        this.repo = repo;
        this.classeService=classeService;
    }

    @Override
    public Utente login(LoginRequest request) {
        Utente u= repo.findByEmailAndPassword(request.getEmail(),request.getPassword()).orElseThrow(UtenteNonTrovatoException::new);
        if(u.isBloccato())throw new UtenteBloccatoException(u);
        u.setUltimoAccesso(LocalDateTime.now());
        repo.save(u);
        return u;
    }

    @Override
    public void registrati(RegistrazioneRequest request) {
        Classe c=classeService.getClasseByCodice(request.getCodiceClasse());
        Utente u=new Utente(request,c);
        repo.save(u);
    }

    @Override
    public void salvaUtente(Utente u) {
        repo.save(u);
    }

    @Override
    public VisualizzaRichiesteResponse visualizzaRichieste() {
        List<Utente> l=repo.visulizzaRichieste();
        return new VisualizzaRichiesteResponse(l);
    }

    @Override
    public void accettaRichieste(IdUtenteBulkRequest request) {
        List<Long> utentiDaAccettare=request.getUtenti().stream().map(IdUtenteRequest::getId).toList();
        List<Utente> utenti=repo.findAllById(utentiDaAccettare);
        utenti.forEach(u->u.setBloccato(false));
        repo.saveAll(utenti);
    }

    @Override
    public void accettaRichiesta(IdUtenteRequest request) {
        Utente u=repo.findById(request.getId()).orElseThrow(UtenteNonTrovatoException::new);
        u.setBloccato(false);
        repo.save(u);
    }
}
