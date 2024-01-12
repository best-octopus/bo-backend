package com.bestoctopus.dearme.exception;

public class NotValidateException extends RuntimeException{
    public NotValidateException(){
        super();
    }
    public NotValidateException(String message) {
        super(message);
    }
}
