package com.example.demo.exception;

public class ClasseNonTrovataException extends RuntimeException{
    public ClasseNonTrovataException(long id){
        super("nessuna classe trovata con l'id "+id);
    }

    public ClasseNonTrovataException(String codiceClasse){
        super("nessuna classe trovata con il codice \""+codiceClasse+"\"");
    }
}
