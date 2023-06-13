package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.domain.account.AccountDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectMemberId;
import com.nhnacademy.minidooray.gateway.gateway.domain.projectmember.ProjectMemberRegister;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import com.nhnacademy.minidooray.gateway.gateway.service.projectMember.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProjectMemberController {
    private final ProjectMemberService projectMemberService;
    private final AccountService accountService;

    @GetMapping("/projects/{id}/projectMember/register")
    public String viewProjectMemberRegister(@PathVariable Long id, Model model){
        List<ProjectMemberId> projectMemberIds = projectMemberService.getMemberIdsByProjectId(id);
        List<AccountDto> accounts = accountService.getAccounts();
        for(ProjectMemberId memberId:projectMemberIds){
            for(AccountDto accountDto:accounts){
                if(accountDto.getAccountId().equals(memberId)){
                    accounts.remove(accountDto);
                }
            }
        }
        model.addAttribute("Accounts", accounts);
        return "projectmembercreate";
    }
    @PostMapping("/projects/projectMember/register")
    public String registerProjectMember(@ModelAttribute ProjectMemberRegister projectMemberRegister){
        if(projectMemberRegister.getAccountId().isEmpty()){
            return "/project";
        }
        projectMemberService.registerProject(projectMemberRegister.getProjectId(),projectMemberRegister.getAccountId());
        return "/project";
    }

}
