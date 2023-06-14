package com.nhnacademy.minidooray.gateway.gateway.service.github;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;


public interface GithubLoginService {
    String login(String code) throws JsonProcessingException;
}
