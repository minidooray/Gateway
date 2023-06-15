package com.nhnacademy.minidooray.gateway.gateway.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskModify {
    private Long projectId;
    private Long taskId;
    private String assignee;
    private String title;
    private String dueDate;
    private List<Long> tag;
    private Long milestone;
    private String content;
}
