package com.example.demo.controller.admin;

import com.example.demo.dto.request.IdUtenteBulkRequest;
import com.example.demo.dto.request.IdUtenteRequest;
import com.example.demo.dto.response.RichiestaResponse;
import com.example.demo.dto.response.VisualizzaRichiesteResponse;
import com.example.demo.service.UtenteService;
import com.example.demo.utils.UtilPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/"+UtilPaths.Admin.ADMIN)
public class UtenteAdminController {


    private final UtenteService service;

    public UtenteAdminController(UtenteService service) {
        this.service = service;
    }

    @GetMapping("/"+ UtilPaths.Admin.VISUALIZZA_RICHIESTE)
    public ResponseEntity<VisualizzaRichiesteResponse> visualizzaRichieste(){

        return ResponseEntity.ok(service.visualizzaRichieste());
    }
    @GetMapping("/"+ UtilPaths.Admin.ACCETTA_RICHIESTA)
    public ResponseEntity<String> accettaUtente(@RequestBody IdUtenteRequest request){
        return null;
    }

    @GetMapping("/"+ UtilPaths.Admin.ACCETTA_RICHIESTE_BULK)
    public ResponseEntity<String> accettaRichiesteBulk(@RequestBody IdUtenteBulkRequest request){
        return null;
    }


}
