package com.example.demo.service;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.CreaClasseDTO;
import com.example.demo.model.Classe;

public interface ClasseService {

    Classe getClasseByCodice(String codice);
    Classe getClasseById(long id);
    ClasseDTO aggiungiClasse(Classe c);
    ClasseDTO aggiungiClasse(CreaClasseDTO request);

}
