package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectRestController {
    private final ProjectService service;
    private final ProjectMemberAdaptor adaptor;
    @GetMapping("/projects/memberId/{id}")
    public List<ProjectDto> getProjects(@PathVariable String id){
        List<ProjectIdDto> ids = adaptor.getProjectIdByMemberId(id).get();
        for(ProjectIdDto d : ids){
            log.info("{}",d.getProjectId());
        }
        List<ProjectDto> projects = service.getProjects();
        List<ProjectDto> matchingProjects = projects.stream()
                .filter(project -> {
                    for (ProjectIdDto idDto : ids) {
                        if (idDto.getProjectId().equals(project.getProjectId())) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
        return matchingProjects;
    }

}
