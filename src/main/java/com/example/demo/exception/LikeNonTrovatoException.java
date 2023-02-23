package com.example.demo.exception;

public class LikeNonTrovatoException extends RuntimeException {
    public LikeNonTrovatoException(){
        super("nessun like al post con questo id da questo utente");
    }
}
