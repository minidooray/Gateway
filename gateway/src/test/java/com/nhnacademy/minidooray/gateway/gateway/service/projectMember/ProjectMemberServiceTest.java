package com.nhnacademy.minidooray.gateway.gateway.service.projectMember;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectMemberId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ProjectMemberServiceTest {
    @Mock
    private ProjectMemberAdaptor projectMemberAdaptor;


    private ProjectMemberService projectMemberService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        projectMemberService = new ProjectMemberServiceImpl(projectMemberAdaptor);
    }
    @Test
    void getProjectIdsByMemberId() {
        // Arrange
        String memberId = "member123";
        List<ProjectIdDto> expectedProjectIds = Arrays.asList(
                new ProjectIdDto(1L),
                new ProjectIdDto(2L)
        );
        when(projectMemberAdaptor.getProjectIdByMemberId(anyString())).thenReturn(Optional.of(expectedProjectIds));

        // Act
        List<ProjectIdDto> result = projectMemberService.getProjectIdsByMemberId(memberId);

        // Assert
        assertEquals(expectedProjectIds, result);

    }

    @Test
    void registerProject() {
        // Arrange
        Long projectId = 1L;
        String memberId = "member123";
        Result expectedResult = new Result("ok");
        when(projectMemberAdaptor.registerProjectMember(anyLong(), anyString())).thenReturn(Optional.of(expectedResult));

        // Act
        Result result = projectMemberService.registerProject(projectId, memberId);

        // Assert
        assertEquals(expectedResult, result);

    }

    @Test
    void getMemberIdsByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<ProjectMemberId> expectedMemberIds = Arrays.asList(
                new ProjectMemberId("member123"),
                new ProjectMemberId("member456")
        );
        when(projectMemberAdaptor.getMemberIdByProjectId(anyLong())).thenReturn(Optional.of(expectedMemberIds));

        // Act
        List<ProjectMemberId> result = projectMemberService.getMemberIdsByProjectId(projectId);

        // Assert
        assertEquals(expectedMemberIds, result);

    }
}