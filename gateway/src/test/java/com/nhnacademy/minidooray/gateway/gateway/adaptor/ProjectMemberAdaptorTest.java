package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.ProjectMemberAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectMemberId;
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

class ProjectMemberAdaptorTest {
    @Mock
    private RestTemplate restTemplate;

    private ProjectMemberAdaptor projectMemberAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectMemberAdaptor = new ProjectMemberAdaptorImpl(restTemplate);
    }
    @Test
    void getProjectIdByMemberId() {
        // Arrange
        String memberId = "member123";
        List<ProjectIdDto> expectedProjectIds = Arrays.asList(
                new ProjectIdDto(1L),
                new ProjectIdDto(2L)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<ProjectIdDto>> response = new ResponseEntity<>(expectedProjectIds, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<ProjectIdDto>>>any(), eq(memberId)))
                .thenReturn(response);

        // Act
        Optional<List<ProjectIdDto>> result = projectMemberAdaptor.getProjectIdByMemberId(memberId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedProjectIds, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<ProjectIdDto>>>any(), eq(memberId));

    }

    @Test
    void registerProjectMember() {
        // Arrange
        Long projectId = 1L;
        String memberId = "member123";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId), eq(memberId)))
                .thenReturn(response);

        // Act
        Optional<Result> result = projectMemberAdaptor.registerProjectMember(projectId, memberId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResult, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId), eq(memberId));

    }

    @Test
    void getMemberIdByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<ProjectMemberId> expectedMemberIds = Arrays.asList(
                new ProjectMemberId("member123"),
                new ProjectMemberId("member456")
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<ProjectMemberId>> response = new ResponseEntity<>(expectedMemberIds, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<ProjectMemberId>>>any(), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<List<ProjectMemberId>> result = projectMemberAdaptor.getMemberIdByProjectId(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedMemberIds, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<ProjectMemberId>>>any(), eq(projectId));

    }
}