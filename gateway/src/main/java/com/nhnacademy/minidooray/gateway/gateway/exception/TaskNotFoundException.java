package com.nhnacademy.minidooray.gateway.gateway.exception;

public class TaskNotFoundException extends Exception{
    public TaskNotFoundException(String message) {
        super("해당 "+message+"를 아이디로 가지는 태스크가 존재하지 않습니다!");
    }
}
