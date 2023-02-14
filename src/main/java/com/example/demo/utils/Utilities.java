package com.example.demo.utils;

public class Utilities {

    public static String generaCodice(String nome){
        int length=3;
        String[] partizione=nome.toUpperCase().toUpperCase().split(" ");
        String codice="";
        int i=0;
        for(;i<partizione.length-1;i++){
            String s=partizione[i];
            codice+=s.length()>length?s.substring(0,length):s;
            codice+="_";
        }
        codice+=partizione[i];
        return codice;
    }
}
