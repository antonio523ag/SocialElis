package com.example.demo.service;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.IdAulaRequest;
import com.example.demo.dto.request.CreaClasseRequest;
import com.example.demo.model.Classe;

public interface ClasseService {

    Classe getClasseByCodice(String codice);
    Classe getClasseById(long id);
    ClasseDTO aggiungiClasse(Classe c);
    ClasseDTO aggiungiClasse(CreaClasseRequest request);
    void chiudiAula(long id);
    void chiudiAula(IdAulaRequest request);

}
