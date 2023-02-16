package com.example.demo.repository;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtenteRepository  extends JpaRepository<Utente,Long> {

    Optional<Utente> findByEmail(String email);
    Optional<Utente> findByEmailAndPassword(String email,String password);
    @Query("select u from Utente u where u.bloccato = false and u.ultimoAccesso is null")
    List<Utente> visulizzaRichieste();


}
