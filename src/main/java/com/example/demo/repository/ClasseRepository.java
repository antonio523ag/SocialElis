package com.example.demo.repository;

import com.example.demo.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository extends JpaRepository<Classe,Long> {
    Optional<Classe> findClasseByCodice(String codice);
    List<Classe> findAllByChiusaIsFalse();
}
