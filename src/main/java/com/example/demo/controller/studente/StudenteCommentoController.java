package com.example.demo.controller.studente;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.dto.request.CommentaPostRequest;
import com.example.demo.dto.request.IdCommentoRequest;
import com.example.demo.model.Utente;
import com.example.demo.service.CommentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+STUDENTE)
@Tag(name = "Gestione commenti", description = "metodi per lo studente per la gestione dei commenti, tutti i metodi hanno bisogno di autenticazione per essere chiamati")

public class StudenteCommentoController {

    private final CommentoService service;

    public StudenteCommentoController(CommentoService service) {
        this.service = service;
    }

    @PostMapping("/"+COMMENTA_POST)
    @Operation(summary = "commenta post",description = "metodo da invocare per commentare un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "commento inserito con successo"),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> commentaPost(@RequestBody CommentaPostRequest request, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.commentaPost(request,u);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/"+ELIMINA_COMMENTO)
    @Operation(summary = "elimina commento",description = "metodo da invocare per eliminare un coomento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "commento eliminato con successo"),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<String> eliminaCommento(@RequestBody IdCommentoRequest request,UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        service.CancellaCommento(request,u);
        return ResponseEntity.ok().build();
    }
}
