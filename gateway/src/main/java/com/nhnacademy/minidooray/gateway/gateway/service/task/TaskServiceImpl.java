package com.nhnacademy.minidooray.gateway.gateway.service.task;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;
import com.nhnacademy.minidooray.gateway.gateway.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskAdaptor adaptor;

    @Override
    public List<TaskDto> getTaskByProjectId(Long id) {
        return adaptor.getTaskByProjectId(id).get();
    }

    @Override
    public TaskDto registerTask(Long projectId, TaskRegister taskRegister) {
        return adaptor.registerTask(projectId,taskRegister).get();
    }

    @Override
    public TaskDto getTask(Long taskId) throws ProjectNotFoundException {
        if(adaptor.getTask(taskId).isPresent()){
            return adaptor.getTask(taskId).get();
        } else {
            throw new ProjectNotFoundException(taskId.toString());
        }
    }
}
