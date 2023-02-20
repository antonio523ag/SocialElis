package com.example.demo.controller;


import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.CercaUtenteRequest;
import com.example.demo.dto.request.ModificaUtenteRequest;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.model.Utente;
import com.example.demo.service.UtenteService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
public class StudenteUtenteController {

    private final UtenteService service;

    public StudenteUtenteController(UtenteService service) {
        this.service = service;
    }

    //TODO scrivere il metodo della modifica del profilo
    @PostMapping("/"+MODIFICA_PROFILO)
    public ResponseEntity<UtenteDTO> modificaProfilo(@RequestBody ModificaUtenteRequest request){
        UtenteDTO u=null;
        return ResponseEntity.ok(u);
    }
    @PostMapping(value = "/"+AGGIUNGI_IMMAGINE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UtenteDTO> aggiungiImmagine(@RequestPart("file") MultipartFile file, UsernamePasswordAuthenticationToken p){
        Utente u=((Utente)p.getPrincipal());
        UtenteDTO ut=service.aggiungiImmagineProfilo(u,file);
        return ResponseEntity.ok().body(ut);
    }

    @PostMapping("/"+CERCA_UTENTE)
    public ResponseEntity<UtenteDTOListResponse> cercaUtente(@RequestBody CercaUtenteRequest request,UsernamePasswordAuthenticationToken p){
        Utente u=(Utente)p.getPrincipal();
        UtenteDTOListResponse response=service.cercaUtente(request,u);
        return ResponseEntity.ok(response);
    }


}
