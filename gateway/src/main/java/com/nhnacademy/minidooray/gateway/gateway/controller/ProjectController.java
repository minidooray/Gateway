package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService service;
    private final ProjectMemberAdaptor adaptor;
    private final TaskAdaptor adaptor2;
    @GetMapping("/project")
    public String viewProjects(Model model, HttpSession session){
        String id = (String) session.getAttribute("username");
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
        model.addAttribute("Projects",matchingProjects);
        return "project";
    }
    @GetMapping("/projects/{id}")
    public String viewTaskByProjectId(@PathVariable Long id,Model model,HttpSession session){
        String id2 = (String) session.getAttribute("username");
        List<ProjectIdDto> ids = adaptor.getProjectIdByMemberId(id2).get();
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
        model.addAttribute("Projects",matchingProjects);
        model.addAttribute("Tasks",adaptor2.getTaskByProjectId(id).get());
        return "project";
    }


}
