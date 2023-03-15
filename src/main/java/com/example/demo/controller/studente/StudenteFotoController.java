package com.example.demo.controller.studente;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.service.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
@Tag(name = "visualizzazione immagini", description = "metodi per il download delle immagini, tutti i metodi hanno bisogno di autenticazione per essere chiamati")
public class StudenteFotoController {
    private final FileStorageService fileService;

    public StudenteFotoController(FileStorageService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/"+VISUALIZZA_FOTO_PROFILO+"/{id}")
    @Operation(summary = "visualizza foto profilo",description = "metodo da invocare per visualizzare una foto profilo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "foto profilo trovata e visualizzabile", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<Resource> visualizzaFotoProfilo(@PathVariable("id") String nomeFile, HttpServletRequest request) {
        Resource img = fileService.findFileByNomeAndTipo(nomeFile,"profilo" );
        return visualizzaFoto(img,request);
    }

    @GetMapping("/"+VISUALIZZA_FOTO_POST+"/{id}")
    @Operation(summary = "visualizza foto post",description = "metodo da invocare per visualizzare una foto presente in un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "foto post trovata e visualizzabile", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Resource.class))),
            @ApiResponse(responseCode = "400", description = "leggere il message per verificare l'errore", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = MessageDTO.class)))
    })
    public ResponseEntity<Resource> visualizzaFotoPost(@PathVariable("id") String nomeFile, HttpServletRequest request) {
        Resource img = fileService.findFileByNomeAndTipo(nomeFile,"post" );
        return visualizzaFoto(img,request);
    }

    private ResponseEntity<Resource> visualizzaFoto(Resource img,HttpServletRequest request){
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(img.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + img.getFilename() + "\"")
                .body(img);
    }
}
