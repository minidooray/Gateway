package com.nhnacademy.minidooray.gateway.gateway.domain.projectmember;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMemberRegister {
    private Long projectId;
    private String AccountId;
}
