package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.config.RedisConfiguration;
import com.nhnacademy.minidooray.gateway.gateway.config.SecurityConfiguration;
import com.nhnacademy.minidooray.gateway.gateway.config.WebConfig;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import com.nhnacademy.minidooray.gateway.gateway.service.github.GithubLoginService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@AutoConfigureMockMvc
@WebMvcTest(controllers = LoginController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = {WebConfig.class, SecurityConfiguration.class, RedisConfiguration.class})})
class LoginControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    private GithubLoginService githubLoginService;
    @MockBean
    private RedisTemplate<String, String> redisTemplate;
    @MockBean
    private AccountService accountService;
    @Test
    void loginForm() throws Exception {
        mvc.perform(get("/login")).andDo(print());
    }

    @Test
    void callbackFromGithub() {
    }

    @Test
    void loginSuccessHandler() {
    }

    @Test
    void logincreateForm() {
    }

    @Test
    void logincreate() {
    }
}