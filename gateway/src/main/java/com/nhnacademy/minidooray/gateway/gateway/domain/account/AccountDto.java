package com.nhnacademy.minidooray.gateway.gateway.domain.account;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String accountId;
    private String accountPwd;
    private String accountName;
    private String accountEmail;
    private String accountStatus;
    private String accountAccessAt;
}
