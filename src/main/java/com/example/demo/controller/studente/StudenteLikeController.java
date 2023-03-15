package com.example.demo.controller.studente;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.dto.request.IdPostRequest;
import com.example.demo.dto.response.ListaUtentiLike;
import com.example.demo.model.Utente;
import com.example.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.utils.UtilPaths.Studente.*;
import static com.example.demo.utils.UtilPaths.Studente.VISUALIZZA_LIKE_DEL_POST;

@RestController
@RequestMapping("/"+ STUDENTE)
@Tag(name = "Gestione like", description = "metodi per lo studente per la gestione dei like, tutti i metodi hanno bisogno di autenticazione per essere chiamati")
public class StudenteLikeController {

    private final PostService service;

    public StudenteLikeController(PostService service) {
        this.service = service;
    }

    @PostMapping("/"+METTI_LIKE_A_POST)
    @Operation(summary = "metti like ad un post",description = "metodo da invocare per mettere like ad un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "like inserito"),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> mettiLikeAPost(@RequestBody IdPostRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.mettiLikeAPost(request,u);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/"+RIMUOVI_LIKE_A_POST)
    @Operation(summary = "rimuovi like da un post",description = "metodo da invocare per rimuovere il like da un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "like rimosso senza problemi"),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> rimuoviLikeDaPost(@RequestBody IdPostRequest request,UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.rimuoviLikeDaPost(request,u);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PostMapping("/"+VISUALIZZA_LIKE_DEL_POST)
    @Operation(summary = "visualizza like del post",description = "metodo da invocare per visualizzare tutti i like presenti su un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "elenco dei like visibile", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ListaUtentiLike.class))),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<ListaUtentiLike> visualizzaLikeDelPost(@RequestBody IdPostRequest request, UsernamePasswordAuthenticationToken token){
        return ResponseEntity.status(HttpStatus.OK).body(service.visualizzaLike(request));
    }
}
