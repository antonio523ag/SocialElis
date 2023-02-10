package com.example.demo.controller.general;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.model.Ruolo;
import com.example.demo.model.Utente;
import com.example.demo.secutiry.JwtService;
import com.example.demo.service.UtenteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import static com.example.demo.utils.UtilPaths.General.*;
@RestController
@RequestMapping("/"+GENERAL)
public class UtenteGeneralController {

    private final JwtService jwtService;
    private final UtenteService utenteService;

    public UtenteGeneralController(JwtService service,UtenteService utenteService) {
        this.jwtService = service;
        this.utenteService=utenteService;
    }


    @PostMapping("/"+REGISTRAZIONE)
    public ResponseEntity<String> registrati(@RequestBody @Valid RegistrazioneRequest request) {
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/"+LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Utente u=utenteService.login(request);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization",jwtService.generateToken(u)).build();

    }

    @PostMapping("/"+RECUPERA_PASSWORD)
    public ResponseEntity<String> recuperaPassword() {
        return null;
    }
    @PostMapping("/"+CONFERMA_RICHIESTA_NUOVA_PASSWORD)
    public ResponseEntity<String> confermaRecuperoPassword() {
        return null;
    }


}