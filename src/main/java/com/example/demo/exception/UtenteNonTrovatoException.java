package com.example.demo.exception;

public class UtenteNonTrovatoException extends RuntimeException {
    public UtenteNonTrovatoException(){
        super("nessun utente con quella username e/o password");
    }

    public UtenteNonTrovatoException(long id){
        super("nessun utente trovato con id "+id);
    }

    public UtenteNonTrovatoException(String username){
        super("nessun utente trovato con username =\""+username+"\"");
    }
}
