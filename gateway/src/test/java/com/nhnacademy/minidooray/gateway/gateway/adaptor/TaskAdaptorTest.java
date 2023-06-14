package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.TaskAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskRegister;
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

class TaskAdaptorTest {
    @Mock
    private RestTemplate restTemplate;

    private TaskAdaptor taskAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskAdaptor = new TaskAdaptorImpl(restTemplate);
    }
    @Test
    void getTaskByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<TaskDto> expectedTasks = Arrays.asList(
                new TaskDto(1L,"Task 1","TaskContent 1","User 1",null,null),
                new TaskDto(2L,"Task 2","TaskContent 2","User 2",null,null)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<TaskDto>> response = new ResponseEntity<>(expectedTasks, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<TaskDto>>>any(), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<List<TaskDto>> result = taskAdaptor.getTaskByProjectId(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedTasks, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<TaskDto>>>any(), eq(projectId));

    }

    @Test
    void registerTask() {
        // Arrange
        Long projectId = 1L;
        TaskRegister taskRegister = new TaskRegister("Task 1","TaskContent 1","User 1","User 2",null,null,1L);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(taskRegister, headers);
        TaskDto expectedTask = new TaskDto(1L,"Task 1","TaskContent 1","User 1",null,null);
        ResponseEntity<TaskDto> response = new ResponseEntity<>(expectedTask, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(TaskDto.class), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<TaskDto> result = taskAdaptor.registerTask(projectId, taskRegister);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedTask, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(TaskDto.class), eq(projectId));

    }

    @Test
    void getTask() {
        // Arrange
        Long taskId = 1L;
        TaskDto expectedTask = new TaskDto(taskId,"Task 1","TaskContent 1","User 1",null,null);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<TaskDto> response = new ResponseEntity<>(expectedTask, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(TaskDto.class), eq(taskId)))
                .thenReturn(response);

        // Act
        Optional<TaskDto> result = taskAdaptor.getTask(taskId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedTask, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(TaskDto.class), eq(taskId));

    }
}