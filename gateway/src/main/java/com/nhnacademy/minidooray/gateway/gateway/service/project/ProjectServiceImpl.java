package com.nhnacademy.minidooray.gateway.gateway.service.project;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
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
}
