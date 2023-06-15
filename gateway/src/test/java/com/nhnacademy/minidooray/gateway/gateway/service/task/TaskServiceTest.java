package com.nhnacademy.minidooray.gateway.gateway.service.task;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;
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

class TaskServiceTest {
    @Mock
    private TaskAdaptor taskAdaptor;


    private TaskService taskService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(taskAdaptor);
    }
    @Test
    void getTaskByProjectId() {

        Long projectId = 1L;
        List<TaskDto> expectedTasks = Arrays.asList(
                new TaskDto(1L,"Task 1","TaskContent 1","User 1",null,null),
                new TaskDto(2L,"Task 2","TaskContent 2","User 2",null,null)
        );

        when(taskAdaptor.getTaskByProjectId(anyLong())).thenReturn(Optional.of(expectedTasks));

        // Act
        List<TaskDto> result = taskService.getTaskByProjectId(projectId);

        // Assert
        assertEquals(expectedTasks, result);
    }

    @Test
    void registerTask() {
        // Arrange
        Long projectId = 1L;
        TaskRegister taskRegister = new TaskRegister("Task 1","TaskContent 1","User 1","User 2",null,null,1L);
        TaskDto expectedTask = new TaskDto(1L,"Task 1","TaskContent 1","User 1",null,null);
        when(taskAdaptor.registerTask(anyLong(), any(TaskRegister.class))).thenReturn(Optional.of(expectedTask));

        // Act
        TaskDto result = taskService.registerTask(projectId, taskRegister);

        // Assert
        assertEquals(expectedTask, result);
    }

    @Test
    void getTask() throws ProjectNotFoundException {
        // Arrange
        Long taskId = 1L;
        TaskDto expectedTask = new TaskDto(1L,"Task 1","TaskContent 1","User 1",null,null);

        when(taskAdaptor.getTask(anyLong())).thenReturn(Optional.of(expectedTask));

        // Act
        TaskDto result = taskService.getTask(taskId);

        // Assert
        assertEquals(expectedTask, result);
    }
    @Test
    void getTaskException(){
        // Arrange
        Long taskId = 999L;
        when(taskAdaptor.getTask(anyLong())).thenReturn(Optional.empty());

        // Assert
        assertThrows(ProjectNotFoundException.class, () -> taskService.getTask(taskId));

    }
}