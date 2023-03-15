package com.example.demo.controller.studente;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.IdPostRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.request.NumeroPaginaRequest;
import com.example.demo.dto.response.ListaUtentiLike;
import com.example.demo.dto.response.UtenteDTOListResponse;
import com.example.demo.dto.response.VisualizzaPostDTO;
import com.example.demo.model.Utente;
import com.example.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
@Tag(name = "Gestione Post", description = "metodi per lo studente per la gestione dei post, tutti i metodi hanno bisogno di autenticazione per essere chiamati")
public class StudentePostController {

    private final PostService service;

    public StudentePostController(PostService service) {
        this.service = service;
    }

    @PostMapping(value = "/"+SCRIVI_POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "scrivi post",description = "metodo da invocareper scrivere un post",security = { @SecurityRequirement(name = "jwt") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "post inserito correttamente", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = PostDTO.class))),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<PostDTO> scriviPost(@RequestPart("text")String request, @RequestPart("file") MultipartFile file, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        PostDTO p=file==null?service.salvaPost(request,u):service.salvaPost(request,file,u);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @PostMapping(value = "/"+VISUALIZZA_POST_UTENTE)
    @Operation(summary = "visualizza post di un utente",description = "metodo da invocare per visualizzare tutti i post pubblicati da uno specifico utente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "elenco dei post visibile", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = VisualizzaPostDTO.class))),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<VisualizzaPostDTO> visualizzaPost(@RequestBody IdUtenteRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        VisualizzaPostDTO r=service.visualizzaPostPerUtente(request,u);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @PostMapping(value = "/"+VISUALIZZA_POST)
    @Operation(summary = "visualizza post",description = "metodo da invocare per visualizzare tutti i post visibili per l'utente che ha effettuato la login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "elenco dei post visibili", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = VisualizzaPostDTO.class))),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<VisualizzaPostDTO> visualizzaPost(@RequestBody NumeroPaginaRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        VisualizzaPostDTO r=service.visualizzaPostHome(request,u);
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }

    @PostMapping(value = "/"+ELIMINA_POST)
    @Operation(summary = "elimina post",description = "metodo da invocare per eliminare un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "post eliminato"),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> eliminaPost(@RequestBody IdPostRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.cancellaPost(request,u);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
