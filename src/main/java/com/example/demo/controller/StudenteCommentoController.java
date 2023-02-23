package com.example.demo.controller;

import com.example.demo.dto.request.CommentaPostRequest;
import com.example.demo.dto.request.IdCommentoRequest;
import com.example.demo.model.Utente;
import com.example.demo.service.CommentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+STUDENTE)
public class StudenteCommentoController {

    private final CommentoService service;

    public StudenteCommentoController(CommentoService service) {
        this.service = service;
    }

    @PostMapping("/"+COMMENTA_POST)
    public ResponseEntity<String> commentaPost(@RequestBody CommentaPostRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.commentaPost(request,u);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/"+ELIMINA_COMMENTO)
    public ResponseEntity<String> eliminaCommento(@RequestBody IdCommentoRequest request,UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.cancellaPost(request,u);
        return ResponseEntity.ok().build();
    }
}
