package com.nhnacademy.minidooray.gateway.gateway.service.account;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService{
    private final AccountAdaptor adaptor;
    @Override
    public AccountDto getAccount(String accountId) {
        return adaptor.getAccountById(accountId).get();
    }

    @Override
    public AccountDto registerAccount(AccountRegister accountRegister) {
        return adaptor.registerAccount(accountRegister).get();
    }

    @Override
    public AccountDto getAccountByEmail(String accountEmail) {
        return adaptor.getAccountByEmail(accountEmail).get();
    }

    @Override
    public Result deleteAccount(String accountId) {
        return adaptor.deleteAccount(accountId);
    }

    @Override
    public List<AccountDto> getAccounts() {
        return adaptor.getAccounts().get();
    }

    @Override
    public AccountDto updateAccount(String accountId, String status) {
        return adaptor.updateAccountById(accountId,status).get();
    }
}
