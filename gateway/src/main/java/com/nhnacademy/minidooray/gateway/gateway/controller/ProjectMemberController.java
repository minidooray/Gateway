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

import java.util.Iterator;
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
        Iterator<AccountDto> iterator = accounts.iterator();
        while (iterator.hasNext()) {
            AccountDto account = iterator.next();
            if (projectMemberIds.stream().anyMatch(memberId -> memberId.getAccountId().equals(account.getAccountId()))) {
                iterator.remove();
            }
        }
        model.addAttribute("Accounts", accounts);
        model.addAttribute("ProjectId", id);
        return "addmember";
    }
    @PostMapping("/projects/projectMember/register")
    public String registerProjectMember(@ModelAttribute ProjectMemberRegister projectMemberRegister){
        if(projectMemberRegister.getSelectedAccounts().isEmpty()){
            return "redirect:/project";
        }
        for(String accountId: projectMemberRegister.getSelectedAccounts()){
            projectMemberService.registerProject(projectMemberRegister.getProjectId(),accountId);
        }
        return "redirect:/projects/"+projectMemberRegister.getProjectId();
    }

}
