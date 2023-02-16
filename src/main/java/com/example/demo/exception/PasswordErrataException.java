package com.example.demo.exception;

public class PasswordErrataException extends RuntimeException{

    public PasswordErrataException(String msg){
        super(msg);
    }
}
