package com.example.demo.controller.studente;


import com.example.demo.dto.general.MessageDTO;
import com.example.demo.dto.general.UtenteDTO;
import com.example.demo.dto.request.CercaUtenteRequest;
import com.example.demo.dto.request.ModificaUtenteRequest;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.model.Utente;
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

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
@Tag(name = "Utente loggato", description = "metodi per la gestione degli utenti da parte di un utente loggato, tutti i metodi hanno bisogno di autenticazione per essere chiamati")
public class StudenteUtenteController {

    private final UtenteService service;

    public StudenteUtenteController(UtenteService service) {
        this.service = service;
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


}
