package com.nhnacademy.minidooray.gateway.gateway.service.account;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    AccountDto getAccount(String accountId);

    AccountDto registerAccount(AccountRegister accountRegister);

    AccountDto getAccountByEmail(String accountEmail);
    Result deleteAccount(String accountId);

    List<AccountDto> getAccounts();

    AccountDto updateAccount(String accountId, String status);
}
