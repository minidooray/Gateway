package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;
import org.springframework.data.redis.stream.Task;

import java.util.List;
import java.util.Optional;

public interface TaskAdaptor {
    Optional<List<TaskDto>> getTaskByProjectId(Long id);
    Optional<TaskDto> registerTask(Long projectId,TaskRegister taskRegister);
}
