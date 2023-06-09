package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/account")
    public AccountDto registerAccount(@RequestBody AccountRegister accountRegister){
        return accountService.registerAccount(accountRegister);
    }

    @GetMapping("/account")
    public List<AccountDto> getAccounts(){
        return accountService.getAccounts();
    }
    @PostMapping("/account/{id}/{status}")
    public AccountDto updateAccountById(@PathVariable(name = "id") String id, @PathVariable(name = "status") String status){
        return accountService.updateAccount(id,status);
    }
    @GetMapping("/account/{id}")
    public AccountDto getAccountById(@PathVariable String id){
        return accountService.getAccount(id);
    }

    @DeleteMapping("/account/{id}")
    public Result deleteAccountById(@PathVariable String id){
        return accountService.deleteAccount(id);
    }
}
