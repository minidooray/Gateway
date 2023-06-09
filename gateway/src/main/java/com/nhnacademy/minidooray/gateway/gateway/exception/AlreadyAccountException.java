package com.nhnacademy.minidooray.gateway.gateway.exception;

public class AlreadyAccountException extends RuntimeException{
    public AlreadyAccountException(String message) {
        super(message);
    }
}
