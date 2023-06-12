package com.nhnacademy.minidooray.gateway.gateway.domain.project;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegisterDto {
    private String title;
    private String content;
}
