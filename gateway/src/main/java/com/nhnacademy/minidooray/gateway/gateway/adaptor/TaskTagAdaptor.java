package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tasktag.TaskTagDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface TaskTagAdaptor{
    Optional<Result> registerTaskTag(Long projectId,Long taskId, Long tagId);
    Optional<List<TaskTagDto>> getTagIdByTaskId(Long taskId);
}
