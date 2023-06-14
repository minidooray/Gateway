package com.nhnacademy.minidooray.gateway.gateway.service.milestone;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.CommentAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.MilestoneAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
import com.nhnacademy.minidooray.gateway.gateway.service.comment.CommentService;
import com.nhnacademy.minidooray.gateway.gateway.service.comment.CommentServiceImpl;
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

class MileServiceTest {
    @Mock
    private MilestoneAdaptor milestoneAdaptor;

    private MileService mileService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mileService = new MileServiceImpl(milestoneAdaptor);
    }
    @Test
    void getMilestonesByProjectId() {
        // Arrange
        Long projectId = 12345L;
        List<MilestoneDto> expectedMilestones = Arrays.asList(
                new MilestoneDto(1L, "milestone 1",null,null),
                new MilestoneDto(2L, "milestone 2",null,null)
        );
        when(milestoneAdaptor.getMilestonesByProjectId(anyLong())).thenReturn(Optional.of(expectedMilestones));

        // Act
        List<MilestoneDto> result = mileService.getMilestonesByProjectId(projectId);

        // Assert
        assertEquals(expectedMilestones, result);

    }

    @Test
    void registerMilestone() {
        // Arrange
        Long projectId = 12345L;
        MilestoneRegisterDto registerDto = new MilestoneRegisterDto("New milestone",null,null);
        Result expectedResult = new Result("ok");
        when(milestoneAdaptor.registerMilestone(anyLong(), any(MilestoneRegisterDto.class))).thenReturn(Optional.of(expectedResult));

        // Act
        Result result = mileService.registerMilestone(projectId, registerDto);

        // Assert
        assertEquals(expectedResult, result);

    }
}