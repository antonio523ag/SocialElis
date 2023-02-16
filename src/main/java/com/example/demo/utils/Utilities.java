package com.example.demo.utils;

import com.example.demo.model.Utente;

public class Utilities {

    public static String generaCodice(String nome){
        int length=3;
        String[] partizione=nome.toUpperCase().toUpperCase().split(" ");
        StringBuilder codice= new StringBuilder();
        int i=0;
        for(;i<partizione.length-1;i++){
            String s=partizione[i];
            codice.append(s.length() > length ? s.substring(0, length) : s);
            codice.append("_");
        }
        codice.append(partizione[i]);
        return codice.toString();
    }


}
