package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
class AccountControllerTest {


    @Autowired
    MockMvc mvc;
    @Test
    @DisplayName("사용자 등록")
    void registerAccount() {
        AccountRegister accountRegister = new AccountRegister("test","1234","test@email.com","테스트");

    }

    @Test
    void getAccounts() {
    }

    @Test
    void updateAccountById() {
    }

    @Test
    void getAccountById() {
    }

    @Test
    void deleteAccountById() {
    }

    @Test
    void getAccountByProject() {
    }
}