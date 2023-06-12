package com.nhnacademy.minidooray.gateway.gateway.service.tasktag;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskTagDto;

import java.util.List;
import java.util.Optional;

public interface TaskTagService {
    Result registerTaskTag(Long projectId, Long taskId, Long tagId);
    List<TaskTagDto> getTagIdByTaskId(Long taskId);
}
