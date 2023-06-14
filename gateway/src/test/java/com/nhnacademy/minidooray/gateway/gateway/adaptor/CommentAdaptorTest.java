package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.AccountAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.CommentAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CommentAdaptorTest {

    @Mock
    private RestTemplate restTemplate;
    private CommentAdaptor commentAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentAdaptor = new CommentAdaptorImpl(restTemplate);
    }

    @Test
    void getCommentsByTaskId() {
        // Arrange
        Long taskId = 1L;
        List<CommentDto> expectedComments = Arrays.asList(
                new CommentDto(1L,"Comment1","User1",null),
                new CommentDto(2L,"Comment2","User2",null)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<CommentDto>> response = new ResponseEntity<>(expectedComments, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<CommentDto>>>any(), eq(taskId)))
                .thenReturn(response);

        // Act
        Optional<List<CommentDto>> result = commentAdaptor.getCommentsByTaskId(taskId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedComments, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<CommentDto>>>any(), eq(taskId));
    }

    @Test
    void registerCommentByTaskId() {
        // Arrange
        Long taskId = 1L;
        CommentRegisterDto commentRegisterDto = new CommentRegisterDto("User1", "Comment 1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<CommentRegisterDto> entity = new HttpEntity<>(commentRegisterDto, headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(taskId)))
                .thenReturn(response);

        // Act
        Optional<Result> result = commentAdaptor.registerCommentByTaskId(taskId, commentRegisterDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResult, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(taskId));
    }
}