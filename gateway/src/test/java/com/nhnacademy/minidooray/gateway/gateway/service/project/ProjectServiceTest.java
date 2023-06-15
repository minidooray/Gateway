package com.nhnacademy.minidooray.gateway.gateway.service.project;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.exception.ProjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ProjectServiceTest {
    @Mock
    private ProjectAdaptor projectAdaptor;

    private ProjectService projectService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectServiceImpl(projectAdaptor);
    }
    @Test
    void getProjects() {
        // Arrange
        List<ProjectDto> expectedProjects = Arrays.asList(
                new ProjectDto(1L,"user1","project1","프로젝트임","test"),
                new ProjectDto(2L,"user2","project2","프로젝트임","test")
        );
        when(projectAdaptor.getProjects()).thenReturn(Optional.of(expectedProjects));

        // Act
        List<ProjectDto> result = projectService.getProjects();

        // Assert
        assertEquals(expectedProjects, result);

    }

    @Test
    void registerProject() {
        // Arrange
        ProjectRegister projectRegister = new ProjectRegister("admin123", "Project 1", "Description 1");

        ProjectDto expectedProject = new ProjectDto(1L,"admin123","project 1","Description 1","test");
        when(projectAdaptor.registerProject(any(ProjectRegister.class))).thenReturn(Optional.of(expectedProject));

        // Act
        ProjectDto result = projectService.registerProject(projectRegister);

        // Assert
        assertEquals(expectedProject, result);

    }

    @Test
    void getProject() throws ProjectNotFoundException {
        // Arrange
        Long projectId = 1L;
        ProjectDto expectedProject = new ProjectDto(1L,"admin123","project 1","Description 1","test");
        when(projectAdaptor.getProjectById(anyLong())).thenReturn(Optional.of(expectedProject));

        // Act
        ProjectDto result = projectService.getProject(projectId);

        // Assert
        assertEquals(expectedProject, result);
    }
    @Test
    void getProjectException() {
        // Arrange
        Long projectId = 999L;
        when(projectAdaptor.getProjectById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProjectNotFoundException.class, () -> projectService.getProject(projectId));

    }
}