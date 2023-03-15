package com.example.demo.controller.admin;

import com.example.demo.dto.request.CreaAdminRequest;
import com.example.demo.dto.request.IdUtenteBulkRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.model.Utente;
import com.example.demo.service.UtenteService;
import com.example.demo.utils.UtilPaths;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/"+UtilPaths.Admin.ADMIN)
@Tag(name = "Gestione Admin", description = "elenco dei metodi dell'admin, l'utente deve essere autenticato come admin per chiamarli")

public class AdminUtenteController {


    private final UtenteService service;

    public AdminUtenteController(UtenteService service) {
        this.service = service;
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
        Utente u=new Utente(request);
        service.salvaUtente(u);
        return ResponseEntity.ok().build();
    }


}
