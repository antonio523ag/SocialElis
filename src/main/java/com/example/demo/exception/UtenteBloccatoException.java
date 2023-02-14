package com.example.demo.exception;

import com.example.demo.model.Utente;

public class UtenteBloccatoException extends RuntimeException {

    public UtenteBloccatoException(Utente u){
        super(u.getUltimoAccesso()==null?"utente ancora non abilitato all'accesso":"utente bloccato da un amministratore, contattare il gestore del sito");
    }
}
