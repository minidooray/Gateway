package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectStatusDto;

import java.util.List;
import java.util.Optional;

public interface ProjectAdaptor {
    Optional<List<ProjectDto>> getProjects();
    Optional<ProjectDto> getProjectById(Long id);

    Optional<ProjectDto> registerProject(ProjectRegister projectRegister);
    Optional<Result> updateProject(Long id, ProjectStatusDto projectStatusDto);
}
