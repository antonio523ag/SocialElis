package com.example.demo.exception.handler;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GestoreEccezioni {

    @ExceptionHandler(ClasseNonTrovataException.class)
    public ResponseEntity<MessageDTO> classeNonTrovataException(ClasseNonTrovataException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<MessageDTO> filerException(FileException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(LikeNonTrovatoException.class)
    public ResponseEntity<MessageDTO> likeNonTrovatoException(LikeNonTrovatoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }
    @ExceptionHandler(NessunCommentoException.class)
    public ResponseEntity<MessageDTO> nessunCommentoException(NessunCommentoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(NessunPermessoVisualizzazioneException.class)
    public ResponseEntity<MessageDTO> nessunPermessoVisualizzazioneException(NessunPermessoVisualizzazioneException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(PasswordErrataException.class)
    public ResponseEntity<MessageDTO> passwordErrataException(PasswordErrataException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(PostException.class)
    public ResponseEntity<MessageDTO> postException(PostException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(PostNonTrovatoException.class)
    public ResponseEntity<MessageDTO> postNonTrovatoException(PostNonTrovatoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(UtenteNonTrovatoException.class)
    public ResponseEntity<MessageDTO> utenteNonTrovatoException(UtenteNonTrovatoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(UtenteBloccatoException.class)
    public ResponseEntity<MessageDTO> utenteBloccatoException(UtenteBloccatoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDTO> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        String message=exception.getMessage();
        int index=message.indexOf("'");
        String testo = "";
        if(index>=0){
            testo=message.substring(index+1);
        }
        index=testo.indexOf("'");
        if(index>=0){
            testo=testo.substring(0,index);
            testo+=" già presente nel sistema";
        }

        MessageDTO m=new MessageDTO(testo.isBlank()?message:testo);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<MessageDTO> tokenScaduto(ExpiredJwtException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(m);
    }

    @ExceptionHandler(ValoreNonValidoException.class)
    public ResponseEntity<MessageDTO> valoreNonValido(ValoreNonValidoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }
}
