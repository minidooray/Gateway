package com.nhnacademy.minidooray.gateway.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRegister {
    private String taskTitle;
    private String taskContent;
    private String taskRegisterId;
    private String taskManagerId;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
    private Long milestoneId;
}
