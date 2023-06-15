package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.time.LocalDate.now;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountAdaptorImpl implements AccountAdaptor {
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    @Override
    public Optional<AccountDto> getAccountById(String accountId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<AccountDto> response;
        try{
             response=restTemplate.exchange("http://localhost:8081/account/{userid}", HttpMethod.GET,entity,AccountDto.class,accountId);
        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(response.getBody());
    }

    @Override
    public Optional<List<AccountDto>> getAccounts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<AccountDto>> exchange;
        try{
            exchange = restTemplate.
                    exchange("http://localhost:8081/account",HttpMethod.GET,entity,new ParameterizedTypeReference<List<AccountDto>>(){});
        } catch (Exception e){
            return Optional.empty();
        }
        return Optional.of(exchange.getBody());
    }


    @Override
    public Optional<AccountDto> registerAccount(AccountRegister accountRegister) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        Map<String, Object> params = new HashMap<>();
        params.put("accountId",accountRegister.getAccountId());
        params.put("accountPwd",passwordEncoder.encode(accountRegister.getAccountPwd()));
        params.put("accountName",accountRegister.getAccountName());
        params.put("accountEmail",accountRegister.getAccountEmail());
        params.put("accountStatus","가입");
        params.put("accountAccessAt",now());
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(params,headers);
        ResponseEntity<AccountDto> response =
                restTemplate.exchange("http://localhost:8081/account", HttpMethod.POST, entity, new ParameterizedTypeReference<AccountDto>() {
                });
        return Optional.of(response.getBody());
    }

    @Override
    public Optional<AccountDto> getAccountByEmail(String accountEmail) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<AccountDto> response =
                restTemplate.exchange("http://localhost:8081/account/by/{email}", HttpMethod.GET,entity,AccountDto.class,accountEmail);
        return Optional.of(response.getBody());
    }

    @Override
    public Result deleteAccount(String accountId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return restTemplate.
                exchange("http://localhost:8081/account/{id}",HttpMethod.DELETE,requestEntity,Result.class,accountId).getBody();
    }

    @Override
    public Optional<AccountDto> updateAccountById(String accountId, String status) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> reqestEntity = new HttpEntity<>(headers);
        ResponseEntity<AccountDto> exchange = restTemplate.
                exchange("http://localhost:8081/account/{id}/{status}",HttpMethod.POST,reqestEntity,AccountDto.class,accountId,status);
        return Optional.of(exchange.getBody());
    }


}
