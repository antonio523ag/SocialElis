package com.example.demo.exception;

public class NessunCommentoException extends RuntimeException {
    public NessunCommentoException(){
        super("nessun commento con l'id selezionato");
    }
}
