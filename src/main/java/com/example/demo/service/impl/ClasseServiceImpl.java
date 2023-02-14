package com.example.demo.service.impl;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.CreaClasseDTO;
import com.example.demo.exception.ClasseNonTrovataException;
import com.example.demo.model.Classe;
import com.example.demo.repository.ClasseRepository;
import com.example.demo.service.ClasseService;
import org.springframework.stereotype.Service;

@Service
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository repo;

    public ClasseServiceImpl(ClasseRepository repo) {
        this.repo = repo;
    }

    @Override
    public Classe getClasseByCodice(String codice) {
        return repo.findClasseByCodice(codice).orElseThrow(()->new ClasseNonTrovataException(codice));

    }

    @Override
    public Classe getClasseById(long id) {
        return repo.findById(id).orElseThrow(()->new ClasseNonTrovataException(id));
    }

    @Override
    public ClasseDTO aggiungiClasse(Classe c) {

        c=repo.save(c);
        return new ClasseDTO(c);
    }

    @Override
    public ClasseDTO aggiungiClasse(CreaClasseDTO request) {
        Classe c=new Classe(request);
        return aggiungiClasse(c);
    }
}
