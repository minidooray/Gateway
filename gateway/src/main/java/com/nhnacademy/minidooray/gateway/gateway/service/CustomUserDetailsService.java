package com.nhnacademy.minidooray.gateway.gateway.service;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountAdaptor adaptor;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto account = adaptor.getAccountById(username).orElseThrow(()->new UsernameNotFoundException(username+"를 가지는 Account를 찾지 못하였습니다."));
        return new User(account.getAccountId(),account.getAccountPwd(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
