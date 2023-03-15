package com.example.demo.service.impl;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.IdAulaRequest;
import com.example.demo.dto.request.CreaClasseRequest;
import com.example.demo.dto.response.ListClasseDTO;
import com.example.demo.exception.ClasseNonTrovataException;
import com.example.demo.model.Classe;
import com.example.demo.model.Utente;
import com.example.demo.repository.ClasseRepository;
import com.example.demo.service.ClasseService;
import com.example.demo.service.UtenteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository repo;
    private final UtenteService service;

    public ClasseServiceImpl(ClasseRepository repo, UtenteService service) {
        this.repo = repo;
        this.service = service;
    }


    @Override
    public ClasseDTO aggiungiClasse(Classe c) {

        c=repo.save(c);
        return new ClasseDTO(c);
    }

    @Override
    public ClasseDTO aggiungiClasse(CreaClasseRequest request) {
        Classe c=new Classe(request);
        return aggiungiClasse(c);
    }

    @Override
    public void chiudiAula(long id) {
        Classe c=repo.findById(id).orElseThrow(()-> new ClasseNonTrovataException("nessuna classe con id "+id));
        List<Utente> alunni=c.getAlunni();
        alunni.forEach(a->a.setClasse(null));
        alunni.forEach(service::salvaUtente);
        c.setChiusa(true);
        c.setAlunni(null);
        repo.save(c);
    }

    @Override
    public void chiudiAula(IdAulaRequest request) {
        chiudiAula(request.getIdAula());
    }

    @Override
    public ListClasseDTO getAuleAperte() {
        return new ListClasseDTO(repo.findAllByChiusaIsFalse());
    }
}
