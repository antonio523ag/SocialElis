package com.example.demo.controller.admin;

import com.example.demo.dto.general.ClasseDTO;
import com.example.demo.dto.request.CreaClasseDTO;
import com.example.demo.model.Ruolo;
import com.example.demo.service.ClasseService;
import com.example.demo.utils.UtilPaths;
import com.example.demo.utils.Utilities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/"+ UtilPaths.Admin.ADMIN)
public class ClasseAdminController {
    //String APRI_AULA="apriAula";
    //String CHIUDI_AULA="chiudiAula";

    private final ClasseService service;

    public ClasseAdminController(ClasseService service) {
        this.service = service;
    }

    @PostMapping("/"+ UtilPaths.Admin.APRI_AULA)
    public ResponseEntity<ClasseDTO> apriAula(@RequestBody CreaClasseDTO request){
        System.out.println("sono passato");
        ClasseDTO c=service.aggiungiClasse(request);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }


}
