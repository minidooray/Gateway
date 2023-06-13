package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.*;
import com.nhnacademy.minidooray.gateway.gateway.exception.ProjectNotFoundException;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import com.nhnacademy.minidooray.gateway.gateway.service.comment.CommentService;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import com.nhnacademy.minidooray.gateway.gateway.service.projectMember.ProjectMemberService;
import com.nhnacademy.minidooray.gateway.gateway.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMemberService memberService;
    private final TaskService taskService;
    private final AccountService accountService;
    private final CommentService commentService;
    @GetMapping("/project")
    public String viewProjects(Model model, HttpSession session){
        String id = (String) session.getAttribute("username");
        List<ProjectIdDto> ids = memberService.getProjectIdsByMemberId(id);
        for(ProjectIdDto d : ids){
            log.info("{}",d.getProjectId());
        }
        List<ProjectDto> projects = projectService.getProjects();
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
    public String viewTaskByProjectId(@PathVariable Long id,Model model,HttpSession session) throws ProjectNotFoundException {
        String id2 = (String) session.getAttribute("username");
        List<ProjectIdDto> ids = memberService.getProjectIdsByMemberId(id2);
        List<ProjectMemberId> memberIds = memberService.getMemberIdsByProjectId(id);


        List<ProjectDto> projects = projectService.getProjects();
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
        List<AccountDto> matchingMembers = new ArrayList<>();
        for(ProjectMemberId projectMemberId:memberIds){
            matchingMembers.add(accountService.getAccount(projectMemberId.getAccountId()));
        }
        model.addAttribute("Accounts",matchingMembers);
        model.addAttribute("Projects",matchingProjects);
        model.addAttribute("Tasks",taskService.getTaskByProjectId(id));
        model.addAttribute("Select", "project");
        model.addAttribute("Project",projectService.getProject(id));
        return "project";
    }
    @GetMapping("/projects/{id}/task/{taskId}")
    public String viewTaskByProjectIdAndTaskId(@PathVariable Long id,@PathVariable Long taskId,Model model,HttpSession session) throws ProjectNotFoundException {
        String id2 = (String) session.getAttribute("username");
        List<ProjectIdDto> ids = memberService.getProjectIdsByMemberId(id2);
        for(ProjectIdDto d : ids){
            log.info("{}",d.getProjectId());
        }
        List<ProjectDto> projects = projectService.getProjects();
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
        model.addAttribute("Tasks",taskService.getTaskByProjectId(id));
        model.addAttribute("Select", "task");
        model.addAttribute("ProjectId",id);
        model.addAttribute("Task",taskService.getTask(taskId));
        model.addAttribute("Comments",commentService.getCommentsByTaskId(taskId));
        return "project";
    }
    @GetMapping("/project/register")
    public String viewProjectRegister(){
        return "projectcreate";
    }
    @PostMapping(value = "/project/register")
    public String projectRegister(@ModelAttribute ProjectRegisterDto projectRegisterDto,HttpSession session){
        ProjectRegister projectRegister =
                new ProjectRegister(session.getAttribute("username").toString(),projectRegisterDto.getTitle(),projectRegisterDto.getContent());
        memberService.registerProject(projectService.registerProject(projectRegister).getProjectId(),projectRegister.getProjectAdminId());
        return "redirect:/project";
    }
}
