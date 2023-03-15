package com.example.demo.controller.general;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.dto.response.ListClasseDTO;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.RegistrazioneRequest;
import com.example.demo.model.Utente;
import com.example.demo.secutiry.GestoreToken;
import com.example.demo.service.ClasseService;
import com.example.demo.service.UtenteService;
import com.example.demo.utils.UtilPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.utils.UtilPaths.General.*;
@RestController
@RequestMapping("/"+GENERAL)
@Tag(name = "metodi liberi", description = "metodi senza bisogno di autorizzazione per essere chiamati")
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
    @Operation(summary = "registra utente",description = "metodo da invocare per registrarsi al sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registrazione effettuata con successo"),
            @ApiResponse(responseCode = "400", description = "nessuna classe col codice inserito oppure esiste gia un utente con quella determinata email", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> registrati(@RequestBody @Valid RegistrazioneRequest request) {
        utenteService.registrati(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @PostMapping("/"+LOGIN)
    @Operation(summary = "login",description = "metodo da invocare per effettuare la login nel sistema e ottenere l'Authorization token per chiamare gli altri metodi")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "login effettuato con successo, nell'header sotto il tag \"Authorization\" troverai il token"),
            @ApiResponse(responseCode = "400", description = "nessun utente con quella determinata email e/o password", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Utente u=utenteService.login(request);
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", gestoreToken.generateToken(u)).build();

    }

    @GetMapping("/"+ UtilPaths.Admin.GET_CLASSI_APERTE)
    @Operation(summary = "visualizza classi aperte",description = "metodo da invocare per ottenere le classi alle quali è possibile richiedere di essere aggiunto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "registrazione effettuata con successo", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = ListClasseDTO.class)))
    })
    public ResponseEntity<ListClasseDTO> getAule(){
        return ResponseEntity.status(HttpStatus.OK).body(classeService.getAuleAperte());
    }


//    @PostMapping("/"+RECUPERA_PASSWORD)
//    public ResponseEntity<String> recuperaPassword() {
//        return null;
//    }
//    @PostMapping("/"+CONFERMA_RICHIESTA_NUOVA_PASSWORD)
//
//    public ResponseEntity<String> confermaRecuperoPassword() {
//        return null;
//    }


}