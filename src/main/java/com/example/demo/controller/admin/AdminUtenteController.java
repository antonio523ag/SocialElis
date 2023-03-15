package com.example.demo.controller.admin;

import com.example.demo.dto.request.CreaAdminRequest;
import com.example.demo.dto.request.CreaClasseRequest;
import com.example.demo.dto.request.IdUtenteBulkRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.model.Classe;
import com.example.demo.model.Utente;
import com.example.demo.service.ClasseService;
import com.example.demo.service.UtenteService;
import com.example.demo.utils.UtilPaths;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/"+UtilPaths.Admin.ADMIN)
@Tag(name = "Metodi Admin", description = "elenco dei metodi dell'admin, l'utente deve essere autenticato come admin per chiamarli")

public class AdminUtenteController {


    private final UtenteService service;
    private final ClasseService classeService;

    public AdminUtenteController(UtenteService service, ClasseService classeService) {
        this.service = service;
        this.classeService = classeService;
    }

    @GetMapping("/"+ UtilPaths.Admin.VISUALIZZA_RICHIESTE)
    public ResponseEntity<VisualizzaRichiesteResponse> visualizzaRichieste(){

        return ResponseEntity.ok(service.visualizzaRichieste());
    }
    @PostMapping("/"+ UtilPaths.Admin.ACCETTA_RICHIESTA)
    public ResponseEntity<String> accettaUtente(@RequestBody IdUtenteRequest request){
        service.accettaRichiesta(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/"+ UtilPaths.Admin.ACCETTA_RICHIESTE_BULK)
    public ResponseEntity<String> accettaRichiesteBulk(@RequestBody IdUtenteBulkRequest request){
        service.accettaRichieste(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/"+ UtilPaths.Admin.CREA_ADMIN)
    public ResponseEntity<String> creaAdmin(@RequestBody CreaAdminRequest request){
        CreaClasseRequest requestClasse=new CreaClasseRequest();
        requestClasse.setNome("Gruppo Admin");
        Classe c=classeService.findClasseByCodice(requestClasse);
        Utente u=new Utente(request);
        u.setClasse(c);
        service.salvaUtente(u);
        return ResponseEntity.ok().build();
    }
}
