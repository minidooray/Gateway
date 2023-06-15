package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;
import org.springframework.data.redis.stream.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface TaskAdaptor {
    Optional<List<TaskDto>> getTaskByProjectId(Long id);
    Optional<TaskDto> registerTask(Long projectId,TaskRegister taskRegister);
    Optional<TaskDto> getTask(Long id);

    Optional<Result> deleteTask(Long id);
    Optional<Result> updateTask(Long projectId, Long taskId, TaskRegister taskRegister);
}
