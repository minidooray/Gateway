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
public class TaskDto {
    private Long taskId;
    private String taskTitle;
    private String taskContent;
    private String taskManagerId;
    private LocalDate taskStartAt;
    private LocalDate taskEndAt;
}
