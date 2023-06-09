package com.nhnacademy.minidooray.gateway.gateway.exception;

public class InvalidAccessCodeException extends RuntimeException {
    public InvalidAccessCodeException(String accessToken) {
        super("Invalid Access Token :" + accessToken);
    }
}
