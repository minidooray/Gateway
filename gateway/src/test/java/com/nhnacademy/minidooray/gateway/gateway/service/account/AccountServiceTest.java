package com.nhnacademy.minidooray.gateway.gateway.service.account;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.TaskTagAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AccountServiceTest {
    @Mock
    private AccountAdaptor accountAdaptor;

    private AccountService accountService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountServiceImpl(accountAdaptor);
    }

    @Test
    void getAccount() {
        // Arrange
        String accountId = "exampleId";
        AccountDto expectedDto = new AccountDto(accountId,"test","test","test","test","test");
        when(accountAdaptor.getAccountById(anyString())).thenReturn(Optional.of(expectedDto));

        // Act
        AccountDto result = accountService.getAccount(accountId);

        // Assert
        assertEquals(expectedDto, result);
        Mockito.verify(accountAdaptor, Mockito.times(1)).getAccountById(anyString());
    }

    @Test
    void registerAccount() {
        // Arrange
        AccountRegister accountRegister = new AccountRegister("exampleId", "test", "test","test");
        AccountDto expectedAccount =new AccountDto("exampleId","test","test","test","test","test");
        when(accountAdaptor.registerAccount(any(AccountRegister.class))).thenReturn(Optional.of(expectedAccount));

        // Act
        AccountDto result = accountService.registerAccount(accountRegister);

        // Assert
        assertEquals(expectedAccount, result);
        Mockito.verify(accountAdaptor, Mockito.times(1)).registerAccount(any(AccountRegister.class));

    }

    @Test
    void getAccountByEmail() {
        // Arrange
        String accountEmail = "john@example.com";
        AccountDto expectedAccount = new AccountDto("exampleId","test","test","test","test","test");
        when(accountAdaptor.getAccountByEmail(anyString())).thenReturn(Optional.of(expectedAccount));

        // Act
        AccountDto result = accountService.getAccountByEmail(accountEmail);

        // Assert
        assertEquals(expectedAccount, result);
        Mockito.verify(accountAdaptor, Mockito.times(1)).getAccountByEmail(anyString());

    }

    @Test
    void deleteAccount() {
        // Arrange
        String accountId = "12345";
        Result expectedResult = new Result("ok");
        when(accountAdaptor.deleteAccount(anyString())).thenReturn(expectedResult);

        // Act
        Result result = accountService.deleteAccount(accountId);

        // Assert
        assertEquals(expectedResult, result);
        Mockito.verify(accountAdaptor, Mockito.times(1)).deleteAccount(anyString());

    }

    @Test
    void getAccounts() {
        // Arrange
        List<AccountDto> expectedAccounts = Arrays.asList(
                new AccountDto("exampleId1","test1","test1","test1","test1","test1"),
                new AccountDto("exampleId2","test2","test2","test2","test2","test2")

        );
        when(accountAdaptor.getAccounts()).thenReturn(Optional.of(expectedAccounts));

        // Act
        List<AccountDto> result = accountService.getAccounts();

        // Assert
        assertEquals(expectedAccounts, result);
        Mockito.verify(accountAdaptor, Mockito.times(1)).getAccounts();

    }

    @Test
    void updateAccount() {
        // Arrange
        String accountId = "12345";
        String status = "ACTIVE";
        AccountDto expectedAccount =  new AccountDto(accountId,"test2","test2","test2",status,"test2");
        when(accountAdaptor.updateAccountById(anyString(), anyString())).thenReturn(Optional.of(expectedAccount));

        // Act
        AccountDto result = accountService.updateAccount(accountId, status);

        // Assert
        assertEquals(expectedAccount, result);
        Mockito.verify(accountAdaptor, Mockito.times(1)).updateAccountById(anyString(), anyString());

    }
}