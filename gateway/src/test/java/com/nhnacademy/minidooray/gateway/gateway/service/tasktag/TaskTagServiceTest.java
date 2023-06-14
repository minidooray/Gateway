package com.nhnacademy.minidooray.gateway.gateway.service.tasktag;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskTagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tasktag.TaskTagDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TaskTagServiceTest {
    @Mock
    private TaskTagAdaptor taskTagAdaptor;

    private TaskTagService taskTagService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        taskTagService = new TaskTagServiceImpl(taskTagAdaptor);
    }
    @Test
    void registerTaskTag() {
        // Arrange
        Long projectId = 1L;
        Long taskId = 2L;
        Long tagId = 3L;
        Result expectedResult = new Result("ok");
        when(taskTagAdaptor.registerTaskTag(anyLong(), anyLong(), anyLong())).thenReturn(Optional.of(expectedResult));

        // Act
        Result result = taskTagService.registerTaskTag(projectId, taskId, tagId);

        // Assert
        assertEquals(expectedResult, result);
    }

    @Test
    void getTagIdByTaskId() {
        // Arrange
        Long taskId = 1L;
        List<TaskTagDto> expectedTaskTags = Arrays.asList(
                new TaskTagDto(1L),
                new TaskTagDto(2L)
        );
        when(taskTagAdaptor.getTagIdByTaskId(anyLong())).thenReturn(Optional.of(expectedTaskTags));

        // Act
        List<TaskTagDto> result = taskTagService.getTagIdByTaskId(taskId);

        // Assert
        assertEquals(expectedTaskTags, result);
    }
}