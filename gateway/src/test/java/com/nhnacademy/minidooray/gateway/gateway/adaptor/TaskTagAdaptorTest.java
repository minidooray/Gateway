package com.nhnacademy.minidooray.gateway.gateway.adaptor;


import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.TaskTagAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tasktag.TaskTagDto;
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

class TaskTagAdaptorTest {
    @Mock
    private RestTemplate restTemplate;

    private TaskTagAdaptor taskTagAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskTagAdaptor = new TaskTagAdaptorImpl(restTemplate);
    }
    @Test
    void registerTaskTag() {
        // Arrange
        Long projectId = 1L;
        Long taskId = 1L;
        Long tagId = 1L;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId), eq(taskId), eq(tagId)))
                .thenReturn(response);

        // Act
        Optional<Result> result = taskTagAdaptor.registerTaskTag(projectId, taskId, tagId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResult, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId), eq(taskId), eq(tagId));

    }

    @Test
    void getTagIdByTaskId() {
        // Arrange
        Long taskId = 1L;
        List<TaskTagDto> expectedTaskTags = Arrays.asList(
                new TaskTagDto(1L),
                new TaskTagDto(2L)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<TaskTagDto>> response = new ResponseEntity<>(expectedTaskTags, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<TaskTagDto>>>any(), eq(taskId)))
                .thenReturn(response);

        // Act
        Optional<List<TaskTagDto>> result = taskTagAdaptor.getTagIdByTaskId(taskId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedTaskTags, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<TaskTagDto>>>any(), eq(taskId));

    }
}