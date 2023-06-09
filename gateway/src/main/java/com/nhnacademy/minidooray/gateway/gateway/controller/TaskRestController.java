package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TaskRestController {
    private final TaskAdaptor adaptor;
    @GetMapping("/projects/{id}/tasks")
    public List<TaskDto> getTasksByProjectId(@PathVariable Long id){
        return adaptor.getTaskByProjectId(id).get();
    }
}
