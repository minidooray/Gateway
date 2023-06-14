package com.nhnacademy.minidooray.gateway.gateway.exception;

public class AlreadyAccountEmailException extends RuntimeException{
    public AlreadyAccountEmailException(String message) {
        super(message);
    }
}
