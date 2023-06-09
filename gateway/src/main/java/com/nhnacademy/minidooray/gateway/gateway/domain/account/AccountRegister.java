package com.nhnacademy.minidooray.gateway.gateway.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegister {
    private String accountId;
    private String accountPwd;
    private String accountEmail;
    private String accountName;
}
