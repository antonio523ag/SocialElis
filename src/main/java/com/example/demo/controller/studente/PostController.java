package com.example.demo.controller.studente;

import com.example.demo.dto.general.PostDTO;
import com.example.demo.dto.request.NuovoPostRequest;
import com.example.demo.model.Utente;
import com.example.demo.utils.UtilPaths;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;

import static com.example.demo.utils.UtilPaths.Studente.*;

@RestController
@RequestMapping("/"+ STUDENTE)
public class PostController {
    /**
     * String VISUALIZZA_POST="visualizzaPost";
     *         String VISUALIZZA_POST_UTENTE="visualizzaPostUtente";
     *         String SCRIVI_POST="scriviPost";
     *         String ELIMINA_POST="eliminaPost";
     */

    @PostMapping(value = "/"+SCRIVI_POST, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PostDTO> scriviPost(@RequestPart("text")String request, @RequestPart("file") MultipartFile file, UsernamePasswordAuthenticationToken token){
        Utente u=(Utente)token.getPrincipal();
        System.out.println(u);
        System.out.println(request);
        System.out.println(file!=null);
        return null;
    }
}
