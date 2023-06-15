package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.ProjectAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectStatusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectAdaptorTest {
    @Mock
    private RestTemplate restTemplate;

    private ProjectAdaptor projectAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectAdaptor = new ProjectAdaptorImpl(restTemplate);
    }
    @Test
    void getProjects() {
        // Arrange
        List<ProjectDto> expectedProjects = Arrays.asList(
                new ProjectDto(1L,"user1","project1","프로젝트임","test"),
                new ProjectDto(2L,"user2","project2","프로젝트임","test")
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<ProjectDto>> response = new ResponseEntity<>(expectedProjects, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<ProjectDto>>>any()))
                .thenReturn(response);

        // Act
        Optional<List<ProjectDto>> result = projectAdaptor.getProjects();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedProjects, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<ProjectDto>>>any());

    }

    @Test
    void getProjectById() {
        // Arrange
        Long projectId = 1L;
        ProjectDto expectedProject = new ProjectDto(1L,"user1","project1","프로젝트임","test");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<ProjectDto> response = new ResponseEntity<>(expectedProject, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(ProjectDto.class), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<ProjectDto> result = projectAdaptor.getProjectById(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedProject, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(ProjectDto.class), eq(projectId));
    }

    @Test
    void registerProject() {
        // Arrange
        ProjectRegister projectRegister = new ProjectRegister("admin123", "Project 1", "Description 1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<ProjectRegister> entity = new HttpEntity<>(projectRegister, headers);
        ProjectDto expectedProject = new ProjectDto(1L,"admin123","project 1","Description 1","test");
        ResponseEntity<ProjectDto> response = new ResponseEntity<>(expectedProject, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(ProjectDto.class)))
                .thenReturn(response);

        // Act
        Optional<ProjectDto> result = projectAdaptor.registerProject(projectRegister);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedProject, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(ProjectDto.class));

    }

    @Test
    void updateProject() {
        // Arrange
        Long projectId = 1L;
        ProjectStatusDto projectStatusDto = new ProjectStatusDto("IN_PROGRESS");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<ProjectStatusDto> entity = new HttpEntity<>(projectStatusDto, headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PATCH), eq(entity), eq(Result.class), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<Result> result = projectAdaptor.updateProject(projectId, projectStatusDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResult, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.PATCH), eq(entity), eq(Result.class), eq(projectId));

    }
}