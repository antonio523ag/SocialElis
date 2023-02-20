package com.example.demo.controller;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.model.Classe;
import com.example.demo.model.Utente;
import com.example.demo.secutiry.GestoreToken;
import com.example.demo.service.ClasseService;
import com.example.demo.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.example.demo.utils.UtilPaths.General.*;
@RestController
@RequestMapping("/"+GENERAL)
public class GeneralUtenteController {

    private final GestoreToken gestoreToken;
    private final UtenteService utenteService;
    private final ClasseService classeService;

    public GeneralUtenteController(GestoreToken service, UtenteService utenteService, ClasseService classeService) {
        this.gestoreToken = service;
        this.utenteService=utenteService;
        this.classeService = classeService;
    }
    @PostMapping("/"+REGISTRAZIONE)
    public ResponseEntity<String> registrati(@RequestBody @Valid RegistrazioneRequest request) {
        utenteService.registrati(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/"+LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Utente u=utenteService.login(request);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", gestoreToken.generateToken(u)).build();

    }

    @PostMapping("/"+RECUPERA_PASSWORD)
    public ResponseEntity<String> recuperaPassword() {
        return null;
    }
    @PostMapping("/"+CONFERMA_RICHIESTA_NUOVA_PASSWORD)

    public ResponseEntity<String> confermaRecuperoPassword() {
        return null;
    }

    @GetMapping("/init")
    public String init(){
        Utente u=new Utente("antonio","grillo","a.grillo@elis.org","Iamroot!1","antonio523");
        utenteService.salvaUtente(u);
        Classe c=new Classe(0,"codice","nome",null, LocalDate.now(),null,false,null);
        classeService.aggiungiClasse(c);
        return  "fatto!";
    }


}