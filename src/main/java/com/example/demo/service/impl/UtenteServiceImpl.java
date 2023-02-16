package com.example.demo.service.impl;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.*;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.exception.ClasseNonTrovataException;
import com.example.demo.exception.PasswordErrataException;
import com.example.demo.exception.UtenteBloccatoException;
import com.example.demo.exception.UtenteNonTrovatoException;
import com.example.demo.model.Classe;
import com.example.demo.model.Utente;
import com.example.demo.repository.ClasseRepository;
import com.example.demo.repository.UtenteRepository;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.UtenteService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    private final UtenteRepository repo;
    private final ClasseRepository classeRepo;
    private final FileStorageService fileService;

    public UtenteServiceImpl(UtenteRepository repo, ClasseRepository classeRepo, FileStorageService fileService) {
        this.repo = repo;
        this.classeRepo = classeRepo;
        this.fileService = fileService;
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
        Classe c=classeRepo.findClasseByCodice(request.getCodiceClasse()).orElseThrow(()-> new ClasseNonTrovataException("nessuna classe con codice "+request.getCodiceClasse()));
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

    @Override
    public UtenteDTO modificaUtente(ModificaUtenteRequest request) {
        Utente u=repo.findById(request.getIdUtente()).orElseThrow(UtenteNonTrovatoException::new);
        if(!request.getPassword().equals(u.getPassword())) throw new PasswordErrataException("la password inserita non corrisponde alla password dell'utente");
        if(request.getUsername()!=null)u.setUsername(request.getUsername());
        if(request.getNuovaPassword()!=null){
            if(request.getPasswordRipetuta()==null)throw new PasswordErrataException("la password va inserita anche nella ripetizione");
            else if(!request.getPassword().equals(request.getPasswordRipetuta()))throw  new PasswordErrataException("le due password devono coincidere");
            else u.setPassword(request.getPassword());
        }
        repo.save(u);
        return new UtenteDTO(u);
    }


    @Override
    public UtenteDTO aggiungiImmagineProfilo(Utente u, MultipartFile img) {
        String path= fileService.salvaFotoProfilo(img,u);
        u.setPathImg(path);
        repo.save(u);
        return new UtenteDTO(u);
    }

    @Override
    public UtenteDTOListResponse cercaUtente(CercaUtenteRequest request) {
        List<Utente> u=repo.findAll().stream().filter(u1->u1.isValid(request.getTestoRicerca())).toList();
        return new UtenteDTOListResponse(u);
    }
}
