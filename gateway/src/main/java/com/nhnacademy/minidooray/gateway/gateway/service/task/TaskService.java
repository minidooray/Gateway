package com.nhnacademy.minidooray.gateway.gateway.service.task;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;
import com.nhnacademy.minidooray.gateway.gateway.exception.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {
    List<TaskDto> getTaskByProjectId(Long id);
    TaskDto registerTask(Long projectId, TaskRegister taskRegister);
    TaskDto getTask(Long taskId) throws ProjectNotFoundException;
    Result deleteTask(Long id);
    Result updateTask(Long projectId, Long taskId, TaskRegister taskRegister);
}
