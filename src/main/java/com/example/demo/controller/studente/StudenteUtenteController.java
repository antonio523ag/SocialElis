package com.example.demo.controller.studente;


import com.example.demo.dto.general.MessageDTO;
import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.CercaUtenteRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.ModificaUtenteRequest;
import com.example.demo.dto.response.SingoloUtenteDTO;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.exception.ClasseNonTrovataException;
import com.example.demo.model.Post;
import com.example.demo.model.Utente;
import com.example.demo.service.PostService;
import com.example.demo.service.UtenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
@Tag(name = "Utente loggato", description = "metodi per la gestione degli utenti da parte di un utente loggato, tutti i metodi hanno bisogno di autenticazione per essere chiamati")
public class StudenteUtenteController {

    private final UtenteService service;
    private final PostService postService;

    public StudenteUtenteController(UtenteService service, PostService postService) {
        this.service = service;
        this.postService = postService;
    }

    @PostMapping("/"+MODIFICA_PROFILO)
    @Operation(summary = "modifica profilo",description = "metodo da invocare per modificare username e o password",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "modifica effettuata con successo"),
            @ApiResponse(responseCode = "400", description = "password inserita errata, username non valido oppure, se richiesta, problema con la nuova password", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class))),
    })
    public ResponseEntity<String> modificaProfilo(@RequestBody ModificaUtenteRequest request,UsernamePasswordAuthenticationToken token){
        Utente loggato= (Utente) token.getPrincipal();
        service.modificaUtente(request,loggato);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/"+AGGIUNGI_IMMAGINE, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "aggiungi immagine profilo",description = "metodo da invocare per settare un'immagine di profilo, una volta modificata la vecchia verrà cancellata dal server",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "aggiunta effettuata con successo, nella response sarà stato inserito il nome dell'immagine per potervi accedere", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = UtenteDTO.class))),
            @ApiResponse(responseCode = "400", description = "file non iserito o problemi nel salvataggio del file", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class))),
    })
    public ResponseEntity<UtenteDTO> aggiungiImmagine(@RequestPart("file") MultipartFile file, UsernamePasswordAuthenticationToken p){
        Utente u=((Utente)p.getPrincipal());
        UtenteDTO ut=service.aggiungiImmagineProfilo(u,file);
        return ResponseEntity.ok().body(ut);
    }

    @PostMapping("/"+CERCA_UTENTE)
    @Operation(summary = "cerca utente",description = "metodo da invocare visualizzare gli utenti in base a una ricerca per nome, cognome, username e email",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "elenco dei risultati", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = UtenteDTOListResponse.class))),
    })
    public ResponseEntity<UtenteDTOListResponse> cercaUtente(@RequestBody CercaUtenteRequest request,UsernamePasswordAuthenticationToken p){
        Utente u=(Utente)p.getPrincipal();
        UtenteDTOListResponse response=service.cercaUtente(request,u);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/"+GET_UTENTE)
    @Operation(summary = "cerca utente per id",description = "metodo da invocare visualizzare l'utente partendo dall'id",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profilo dell'utente", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = SingoloUtenteDTO.class))),
            @ApiResponse(responseCode = "400", description = "nessun utente con quell'id",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<SingoloUtenteDTO> cercaUtentePerId(@RequestBody IdUtenteRequest request, UsernamePasswordAuthenticationToken p){
        Utente u=(Utente)p.getPrincipal();
        Utente response=service.getUtenteById(request,u);
        List<Post> post=postService.findByUtenteId(response.getId());
        return ResponseEntity.ok(new SingoloUtenteDTO(response,post));
    }

    @GetMapping("/"+VISUALIZZA_PROFILO)
    @Operation(summary = "visualizza Profilo",description = "metodo da invocare visualizzare il profilo dell'utente",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profilo dell'utente", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = SingoloUtenteDTO.class))),
            @ApiResponse(responseCode = "400", description = "nessun utente con quell'id",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<SingoloUtenteDTO> visualizzaProfilo(UsernamePasswordAuthenticationToken p){
        Utente u=(Utente)p.getPrincipal();
        List<Post> post=postService.findByUtenteId(u.getId());
        return ResponseEntity.ok(new SingoloUtenteDTO(u,post));
    }

    @GetMapping("/"+VISUALIZZA_PARTECIPANTI_AULA)
    @Operation(summary = "visualizza partecipanti aula",description = "metodo da invocare visualizzare gli altri utenti presenti nell'aula",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "profilo dell'utente", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = UtenteDTOListResponse.class))),
            @ApiResponse(responseCode = "400", description = "nessun utente con quell'id",content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<UtenteDTOListResponse> trovaUtentiByAula(UsernamePasswordAuthenticationToken p){
        Utente u=(Utente)p.getPrincipal();
        if(u.getClasse()==null)throw new ClasseNonTrovataException("nessuna aula per questo utente");
        List<Utente> utenti=service.getUtenteByIdClasse(u.getClasse().getId());
        return ResponseEntity.ok(new UtenteDTOListResponse(utenti));
    }








}
