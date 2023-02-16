package com.example.demo.exception.handler;

import com.example.demo.dto.general.MessageDTO;
import com.example.demo.exception.ClasseNonTrovataException;
import com.example.demo.exception.PasswordErrataException;
import com.example.demo.exception.UtenteBloccatoException;
import com.example.demo.exception.UtenteNonTrovatoException;
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
    @ExceptionHandler(UtenteNonTrovatoException.class)
    public ResponseEntity<MessageDTO> utenteNonTrovatoException(UtenteNonTrovatoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<MessageDTO> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
    }

    @ExceptionHandler(UtenteBloccatoException.class)
    public ResponseEntity<MessageDTO> utenteBloccatoException(UtenteBloccatoException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(PasswordErrataException.class)
    public ResponseEntity<MessageDTO> passwordErrataException(PasswordErrataException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(m);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<MessageDTO> tokenScaduto(ExpiredJwtException exception){
        MessageDTO m=new MessageDTO(exception);
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(m);
    }
}
