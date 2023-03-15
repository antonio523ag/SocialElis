package com.example.demo.controller.admin;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.IdAulaRequest;
import com.example.demo.dto.request.CreaClasseRequest;
import com.example.demo.service.ClasseService;
import com.example.demo.utils.UtilPaths;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/"+ UtilPaths.Admin.ADMIN)
@Tag(name = "Metodi Admin", description = "elenco dei metodi dell'admin, l'utente deve essere autenticato come admin per chiamarli")
public class AdminClasseController {

    private final ClasseService service;

    public AdminClasseController(ClasseService service) {
        this.service = service;
    }

    @PostMapping("/"+ UtilPaths.Admin.APRI_AULA)
    public ResponseEntity<ClasseDTO> apriAula(@RequestBody CreaClasseRequest request){
        ClasseDTO c=service.aggiungiClasse(request);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @PostMapping("/"+ UtilPaths.Admin.CHIUDI_AULA)
    public ResponseEntity<String> chiudiAula(@RequestBody IdAulaRequest request){
        service.chiudiAula(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
