package com.example.demo.exception;

public class PostNonTrovatoException extends RuntimeException{
    public PostNonTrovatoException(){
        super("nessun post trovato");
    }
}
