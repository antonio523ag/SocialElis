package com.example.demo.controller.studente;

import com.example.demo.service.FileStorageService;
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
public class FotoController {
    private final FileStorageService fileService;

    public FotoController( FileStorageService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/"+VISUALIZZA_FOTO_PROFILO+"/{id}")
    public ResponseEntity<Resource> visualizzaFotoProfilo(@PathVariable("id") String nomeFile, HttpServletRequest request) {
        Resource img = fileService.findFileByNomeAndTipo(nomeFile,"profilo" );
        return visualizzaFoto(img,request);
    }

    @GetMapping("/"+VISUALIZZA_FOTO_POST+"/{id}")
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
