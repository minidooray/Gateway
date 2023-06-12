package com.nhnacademy.minidooray.gateway.gateway.service.task;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<TaskDto> getTaskByProjectId(Long id);
    TaskDto registerTask(Long projectId, TaskRegister taskRegister);
}
