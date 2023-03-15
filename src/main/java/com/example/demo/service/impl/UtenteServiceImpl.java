package com.example.demo.service.impl;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.*;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.exception.*;
import com.example.demo.model.Classe;
import com.example.demo.model.Ruolo;
import com.example.demo.model.Utente;
import com.example.demo.repository.ClasseRepository;
import com.example.demo.repository.CustomRepository;
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
    private final CustomRepository repoCustom;

    public UtenteServiceImpl(UtenteRepository repo, ClasseRepository classeRepo, FileStorageService fileService, CustomRepository repoCustom) {
        this.repo = repo;
        this.classeRepo = classeRepo;
        this.fileService = fileService;
        this.repoCustom = repoCustom;
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
    public Utente getUtenteById(IdUtenteRequest request,Utente richiedente) {
        Utente u= repo.findById(request.getId()).orElseThrow(()->new UtenteNonTrovatoException(request.getId()));
        if(richiedente.getRuolo()!= Ruolo.GESTORE){
            if(richiedente.getClasse()==null&&u.getClasse()!=null)throw new NessunPermessoVisualizzazioneException("non puoi visualizzare i post di persone che sono ancora in ambiente di testing");
            else if(richiedente.getClasse()!=null&&u.getClasse()==null) throw new NessunPermessoVisualizzazioneException("sei ancora in ambiente di testing, non puoi visualizzare i post in ambiente comune");
            else if(richiedente.getClasse()!=null && u.getClasse().getId()!=richiedente.getClasse().getId())throw new NessunPermessoVisualizzazioneException("non siete nella stessa classe, non puoi visualizzare i post di questo utente");
        }
        return u;
    }

    @Override
    public void registrati(RegistrazioneRequest request) {
        if(!request.getPassword().equals(request.getPasswordRipetuta())) throw new PasswordErrataException("le password non corrispondono");
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
        List<Utente> utenti=repo.findAllById(request.getIdUtenti());
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
    public void modificaUtente(ModificaUtenteRequest request, Utente u) {
        if(!request.getPassword().equals(u.getPassword())) throw new PasswordErrataException("la password inserita non corrisponde alla password dell'utente");
        if(request.getUsername()!=null)u.setUsername(request.getUsername());
        if(request.getNuovaPassword()!=null){
            if(!request.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"))throw new PasswordErrataException("la password non rispetta il path");
            else if(request.getPasswordRipetuta()==null)throw new PasswordErrataException("la password va inserita anche nella ripetizione");
            else if(!request.getPassword().equals(request.getPasswordRipetuta()))throw  new PasswordErrataException("le due password devono coincidere");
            else u.setPassword(request.getPassword());
        }
        repo.save(u);
    }


    @Override
    public UtenteDTO aggiungiImmagineProfilo(Utente u, MultipartFile img) {
        String path= fileService.salvaFotoProfilo(img,u);
        u.setPathImg(path);
        repo.save(u);
        return new UtenteDTO(u);
    }

    @Override
    public UtenteDTOListResponse cercaUtente(CercaUtenteRequest request, Utente richiedente) {
        List<Utente> u=repoCustom.getUtentiFiltrati(request,richiedente);
        return new UtenteDTOListResponse(u);
    }

    @Override
    public List<Utente> getUtenteByIdClasse(long id) {
        return repo.findUtenteByClasse_Id(id);
    }
}
