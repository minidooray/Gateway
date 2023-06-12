package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskTagDto;

import java.util.List;
import java.util.Optional;

public interface TaskTagAdaptor{
    Optional<Result> registerTaskTag(Long projectId,Long taskId, Long tagId);
    Optional<List<TaskTagDto>> getTagIdByTaskId(Long taskId);
}
