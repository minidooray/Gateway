package com.nhnacademy.minidooray.gateway.gateway.service.project;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectAdaptor adaptor;
    @Override
    public List<ProjectDto> getProjects() {
        return adaptor.getProjects().get();
    }

    @Override
    public ProjectDto registerProject(ProjectRegister projectRegister) {
        return adaptor.registerProject(projectRegister).get();
    }

    @Override
    public ProjectDto getProject(Long id) throws ProjectNotFoundException {
        if(adaptor.getProjectById(id).isPresent()){
            return adaptor.getProjectById(id).get();
        } else {
            throw new ProjectNotFoundException(id.toString());
        }
    }
}
