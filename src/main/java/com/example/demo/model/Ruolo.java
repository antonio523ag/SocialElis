package com.example.demo.model;

public enum Ruolo {
    STUDENTE("ROLE_STUDENTE"),
    GESTORE("ROLE_GESTORE");



    private String ruolo;
    Ruolo(String ruolo){
        this.ruolo=ruolo;
    }

    public String getNome(){
        return ruolo;
    }

    public String getNomeTrimmed(){
        return ruolo.substring(5);
    }

}
