package com.nhnacademy.minidooray.gateway.gateway.domain.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRegister {
    private Long projectId;
    private Long taskId;
    private String commentContent;
}
