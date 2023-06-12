package com.nhnacademy.minidooray.gateway.gateway.service.project;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    List<ProjectDto> getProjects();
    ProjectDto registerProject(ProjectRegister projectRegister);
    ProjectDto getProject(Long id);
}
