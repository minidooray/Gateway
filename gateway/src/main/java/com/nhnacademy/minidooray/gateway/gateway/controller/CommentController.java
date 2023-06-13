package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
import com.nhnacademy.minidooray.gateway.gateway.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/projects/task/comment/register")
    public String registerComment(@ModelAttribute CommentRegister commentRegister, HttpSession session){
        commentService.registerCommentByTaskId(commentRegister.getTaskId(),new CommentRegisterDto(commentRegister.getCommentContent(),session.getAttribute("username").toString()));
        return "redirect:/projects/"+commentRegister.getProjectId()+"/task/"+commentRegister.getTaskId();
    }
}
