package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
import com.nhnacademy.minidooray.gateway.gateway.exception.ProjectNotFoundException;
import com.nhnacademy.minidooray.gateway.gateway.service.milestone.MileService;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MilestoneController {
    private final MileService milestoneService;
    private final ProjectService projectService;

    @GetMapping("projects/{id}/milestone/register")
    public String viewRegisterMilestone(@PathVariable Long id, Model model) throws ProjectNotFoundException {
        model.addAttribute("Project",projectService.getProject(id));
        return "milestonecreate";
    }
    @PostMapping("projects/milestone/register")
    public String registerMilestone(@ModelAttribute MilestoneRegister milestoneRegister){
        milestoneService.registerMilestone(milestoneRegister.getProjectId(),new MilestoneRegisterDto(milestoneRegister.getMilestoneContent(), LocalDate.now(), LocalDate.parse(milestoneRegister.getMilestoneEndAt(), DateTimeFormatter.ISO_DATE)));
        return "redirect:/projects/"+milestoneRegister.getProjectId();
    }
}
