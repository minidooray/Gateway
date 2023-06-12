package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final ProjectService service;
    private final ProjectMemberAdaptor adaptor;

    private final AccountAdaptor adaptor3;

    @GetMapping("/project/register")
    public String createProject(){

        return "projectcreate";
    }


    // Post 프로젝트생성
    @PostMapping("/project/register")
    public String postProjectRegister(){

        return "redirect:/project";
    }

    //Get task생성
    @GetMapping("/task/register")
    public String createTask(Model model, HttpSession session){
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

        List<AccountDto> AllAcount = adaptor3.getAccounts().get();
        model.addAttribute("Accounts",AllAcount);
        return "taskcreate";
    }

    @PostMapping("/task/register")
    public String postTaskResister(){

        return "redirect:/project";
    }



}
