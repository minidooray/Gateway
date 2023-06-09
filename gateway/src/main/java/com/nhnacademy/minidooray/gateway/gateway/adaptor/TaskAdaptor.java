package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;

import java.util.List;
import java.util.Optional;

public interface TaskAdaptor {
    Optional<List<TaskDto>> getTaskByProjectId(Long id);
}
