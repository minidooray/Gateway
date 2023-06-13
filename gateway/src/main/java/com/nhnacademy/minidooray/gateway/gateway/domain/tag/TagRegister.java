package com.nhnacademy.minidooray.gateway.gateway.domain.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagRegister {
    private Long projectId;
    private String tagContent;
}
