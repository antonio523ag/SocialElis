package com.example.demo.service;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.IdAulaRequest;
import com.example.demo.dto.request.CreaClasseRequest;
import com.example.demo.dto.response.ListClasseDTO;
import com.example.demo.model.Classe;

public interface ClasseService {

    ClasseDTO aggiungiClasse(Classe c);
    ClasseDTO aggiungiClasse(CreaClasseRequest request);
    void chiudiAula(long id);
    void chiudiAula(IdAulaRequest request);

    ListClasseDTO getAuleAperte();


    Classe findClasseByCodice(CreaClasseRequest request);
}
