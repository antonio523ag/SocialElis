package com.example.demo.controller;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.IdPostRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.NumeroPaginaRequest;
import com.example.demo.dto.response.ListaUtentiLike;
import com.example.demo.dto.response.VisualizzaPostDTO;
import com.example.demo.model.Utente;
import com.example.demo.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
public class StudentePostController {
    /**
     * String METTI_LIKE_A_POST="mettiLikeAPost";
     * String RIMUOVI_LIKE_A_POST="rimuoviLikeAPost";
     * String VISUALIZZA_LIKE_DEL_POST="visualizzaLikeDelPost";
     */

    private final PostService service;

    public StudentePostController(PostService service) {
        this.service = service;
    }

    @PostMapping(value = "/"+SCRIVI_POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDTO> scriviPost(@RequestPart("text")String request, @RequestPart("file") MultipartFile file, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        PostDTO p=file==null?service.salvaPost(request,u):service.salvaPost(request,file,u);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @PostMapping(value = "/"+VISUALIZZA_POST_UTENTE)
    public ResponseEntity<VisualizzaPostDTO> visualizzaPost(@RequestBody IdUtenteRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        VisualizzaPostDTO r=service.visualizzaPostPerUtente(request,u);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @PostMapping(value = "/"+VISUALIZZA_POST)
    public ResponseEntity<VisualizzaPostDTO> visualizzaPost(@RequestBody NumeroPaginaRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        VisualizzaPostDTO r=service.visualizzaPostHome(request,u);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @PostMapping(value = "/"+ELIMINA_POST)
    public ResponseEntity<String> eliminaPost(@RequestBody IdPostRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.cancellaPost(request,u);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/"+METTI_LIKE_A_POST)
    public ResponseEntity<String> mettiLikeAPost(@RequestBody IdPostRequest request,UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.mettiLikeAPost(request,u);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/"+RIMUOVI_LIKE_A_POST)
    public ResponseEntity<String> rimuoviLikeDaPost(@RequestBody IdPostRequest request,UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.rimuoviLikeDaPost(request,u);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/"+VISUALIZZA_POST)
    public ResponseEntity<ListaUtentiLike> visualizzaLikeDelPost(@RequestBody IdPostRequest request,UsernamePasswordAuthenticationToken token){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizzaLike(request));
    }
}
