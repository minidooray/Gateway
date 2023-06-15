package com.nhnacademy.minidooray.gateway.gateway.controller;

import com.nhnacademy.minidooray.gateway.gateway.config.RedisConfiguration;
import com.nhnacademy.minidooray.gateway.gateway.config.SecurityConfiguration;
import com.nhnacademy.minidooray.gateway.gateway.config.WebConfig;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import com.nhnacademy.minidooray.gateway.gateway.service.comment.CommentService;
import com.nhnacademy.minidooray.gateway.gateway.service.milestone.MileService;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import com.nhnacademy.minidooray.gateway.gateway.service.projectMember.ProjectMemberService;
import com.nhnacademy.minidooray.gateway.gateway.service.tag.TagService;
import com.nhnacademy.minidooray.gateway.gateway.service.task.TaskService;
import com.nhnacademy.minidooray.gateway.gateway.service.tasktag.TaskTagService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = ProjectController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = {WebConfig.class, SecurityConfiguration.class, RedisConfiguration.class})})

class ProjectControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    private ProjectService projectService;
    @MockBean
    private ProjectMemberService memberService;
    @MockBean
    private TaskService taskService;
    @MockBean
    private AccountService accountService;
    @MockBean
    private TagService tagService;
    @MockBean
    private MileService mileService;
    @MockBean
    private CommentService commentService;
    @MockBean
    private TaskTagService taskTagService;
    @Test
    @WithMockUser(username = "admin22", roles = "USER")
    void viewProjects() throws Exception {
        mvc.perform(get("/project")).
                andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin22", roles = "USER")
    void viewTaskByProjectId() throws Exception {

    }

    @Test
    void viewTaskByProjectIdAndTaskId() {
    }

    @Test
    void viewProjectRegister() {
    }

    @Test
    void projectRegister() {
    }
}