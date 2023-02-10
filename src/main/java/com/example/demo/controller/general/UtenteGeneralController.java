package com.example.demo.controller.general;

import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.model.Ruolo;
import com.example.demo.model.Utente;
import com.example.demo.secutiry.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/general")
public class UtenteGeneralController {

    private final JwtService service;

    public UtenteGeneralController(JwtService service) {
        this.service = service;
    }

    @PostMapping("/registrazione")
    public ResponseEntity<String> registrati(@RequestBody @Valid RegistrazioneRequest request) {
        System.out.println(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Utente u=new Utente(1,"nome","cognome","email","password","dfoibn","fsoigv", Ruolo.GESTORE,null, LocalDateTime.now(),false,null);
        if(u.getEmail().equals(request.getEmail())&&u.getPassword().equals(request.getPassword())){
            return ResponseEntity.status(HttpStatus.OK).header("Authorization",service.generateToken(u)).build();
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    public ResponseEntity<String> recuperaPassword() {
        return null;
    }

    public ResponseEntity<String> confermaRecuperoPassword() {
        return null;
    }


}