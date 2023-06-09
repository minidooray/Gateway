package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;

import java.util.List;
import java.util.Optional;

public interface AccountAdaptor {

    Optional<AccountDto> getAccountById(String accountId);
    Optional<List<AccountDto>> getAccounts();
    Optional<AccountDto> registerAccount(AccountRegister accountRegister);
    Optional<AccountDto> getAccountByEmail(String accountEmail);
    Result deleteAccount(String accountId);
    Optional<AccountDto> updateAccountById(String accountId,String status);
}
