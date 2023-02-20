package com.example.demo.exception;

public class PostException extends RuntimeException{
    public PostException(long id){
        super("nessun post con l'id "+id);
    }

    public PostException(String utente){
        super("il post non è stato pubblicato dall'utente "+utente);
    }
}
