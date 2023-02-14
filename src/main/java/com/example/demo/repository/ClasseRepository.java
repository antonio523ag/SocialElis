package com.example.demo.repository;

import com.example.demo.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClasseRepository extends JpaRepository<Classe,Long> {
    public Optional<Classe> findClasseByCodice(String codice);
}
