package com.nhnacademy.minidooray.gateway.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRegisterDto {
    private Long projectName;
    private String assignee;
    private String title;
    private String dueDate;
    private List<Long> tag;
    private Long milestone;
    private String content;
}
