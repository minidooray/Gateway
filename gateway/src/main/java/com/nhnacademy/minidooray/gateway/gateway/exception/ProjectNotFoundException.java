package com.nhnacademy.minidooray.gateway.gateway.exception;

public class ProjectNotFoundException extends Exception{
    public ProjectNotFoundException(String message) {
        super("해당 "+message+"를 아이디로 가지는 프로젝트가 존재하지 않습니다!");
    }
}
