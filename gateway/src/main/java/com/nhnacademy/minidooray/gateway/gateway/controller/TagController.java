package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;
import com.nhnacademy.minidooray.gateway.gateway.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TagController {
    private final TagService tagService;
    @GetMapping("/projects/{id}/tag/register")
    public String viewRegisterTag(@PathVariable Long id, Model model){
        model.addAttribute("ProjectId", id);
        model.addAttribute("Select","tag");
        return "chores";
    }

    @PostMapping("/projects/tag/register")
    public String registerTag(@ModelAttribute TagRegister tagRegister){
        tagService.registerTag(tagRegister.getProjectId(),new TagRegisterDto(tagRegister.getTagContent()));
        return "redirect:/projects/"+tagRegister.getProjectId();
    }
}
