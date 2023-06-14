package com.nhnacademy.minidooray.gateway.gateway.service.tasktag;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskTagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tasktag.TaskTagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskTagServiceImpl implements TaskTagService {
    private final TaskTagAdaptor adaptor;

    @Override
    public Result registerTaskTag(Long projectId, Long taskId, Long tagId) {
        return adaptor.registerTaskTag(projectId,taskId,tagId).get();
    }

    @Override
    public List<TaskTagDto> getTagIdByTaskId(Long taskId) {
        return adaptor.getTagIdByTaskId(taskId).get();
    }
}
