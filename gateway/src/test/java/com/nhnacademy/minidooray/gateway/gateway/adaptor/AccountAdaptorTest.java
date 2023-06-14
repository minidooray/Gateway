package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.AccountAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AccountAdaptorTest {
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RestTemplate restTemplate;

    private AccountAdaptor accountAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountAdaptor = new AccountAdaptorImpl(passwordEncoder, restTemplate);
    }

    @Test
    void getAccountById() {
        // Arrange
        String accountId = "exampleId";
        AccountDto expectedDto = new AccountDto(accountId,"test","test","test","test","test");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<AccountDto> response = new ResponseEntity<>(expectedDto, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(AccountDto.class), eq(accountId)))
                .thenReturn(response);

        // Act
        Optional<AccountDto> result = accountAdaptor.getAccountById(accountId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDto, result.get());
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(AccountDto.class), eq(accountId));
    }
    @Test
    void getAccountByIdException(){
        String accountId = "invalidId";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(AccountDto.class), eq(accountId)))
                .thenThrow(new RuntimeException()); // 예외 발생시킴

        // Act
        Optional<AccountDto> result = accountAdaptor.getAccountById(accountId);

        // Assert
        assertFalse(result.isPresent());
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(AccountDto.class), eq(accountId));
    }
    @Test
    void getAccounts() {
        // Arrange
        List<AccountDto> expectedList = new ArrayList<>();
        expectedList.add(new AccountDto("id1","test","test","test","test","test"));
        expectedList.add(new AccountDto("id2","test","test","test","test","test"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<AccountDto>> response = new ResponseEntity<>(expectedList, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), any(ParameterizedTypeReference.class)))
                .thenReturn(response);

        // Act
        Optional<List<AccountDto>> result = accountAdaptor.getAccounts();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedList, result.get());
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), any(ParameterizedTypeReference.class));

    }
    @Test
    void getAccountsException(){
        // Arrange
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), any(ParameterizedTypeReference.class)))
                .thenThrow(new RuntimeException()); // 예외 발생시킴

        // Act
        Optional<List<AccountDto>> result = accountAdaptor.getAccounts();

        // Assert
        assertFalse(result.isPresent());
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), any(ParameterizedTypeReference.class));

    }


    @Test
    void getAccountByEmail() {
        // Arrange
        String accountEmail = "john@example.com";
        AccountDto expectedDto = new AccountDto("john", "encodedPassword", "John Doe", "john@example.com","가입","test");;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<AccountDto> response = new ResponseEntity<>(expectedDto, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(AccountDto.class), eq(accountEmail)))
                .thenReturn(response);

        // Act
        Optional<AccountDto> result = accountAdaptor.getAccountByEmail(accountEmail);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDto, result.get());
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(AccountDto.class), eq(accountEmail));

    }

    @Test
    void deleteAccount() {
        // Arrange
        String accountId = "exampleId";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.DELETE), eq(requestEntity), eq(Result.class), eq(accountId)))
                .thenReturn(response);

        // Act
        Result result = accountAdaptor.deleteAccount(accountId);

        // Assert
        assertEquals(expectedResult, result);
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.DELETE), eq(requestEntity), eq(Result.class), eq(accountId));

    }

    @Test
    void updateAccountById() {
        // Arrange
        String accountId = "exampleId";
        String status = "active";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        AccountDto expectedDto = new AccountDto(accountId, "encodedPassword", "John Doe", "john@example.com","가입","test");;
        ResponseEntity<AccountDto> response = new ResponseEntity<>(expectedDto, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(requestEntity), eq(AccountDto.class), eq(accountId), eq(status)))
                .thenReturn(response);

        // Act
        Optional<AccountDto> result = accountAdaptor.updateAccountById(accountId, status);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedDto, result.get());
        verify(restTemplate, times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(requestEntity), eq(AccountDto.class), eq(accountId), eq(status));
    }
}