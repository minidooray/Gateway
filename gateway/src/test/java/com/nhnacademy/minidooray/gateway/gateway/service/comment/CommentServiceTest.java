package com.nhnacademy.minidooray.gateway.gateway.service.comment;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.AccountAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.CommentAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountService;
import com.nhnacademy.minidooray.gateway.gateway.service.account.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CommentServiceTest {
    @Mock
    private CommentAdaptor commentAdaptor;

    private CommentService commentService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentServiceImpl(commentAdaptor);
    }
    @Test
    void getCommentsByTaskId() {
        // Arrange
        Long taskId = 12345L;
        List<CommentDto> expectedComments = Arrays.asList(
                new CommentDto(1L,"Comment1","User1",null),
                new CommentDto(2L,"Comment2","User2",null)
        );
        when(commentAdaptor.getCommentsByTaskId(anyLong())).thenReturn(Optional.of(expectedComments));

        // Act
        List<CommentDto> result = commentService.getCommentsByTaskId(taskId);

        // Assert
        assertEquals(expectedComments, result);
        Mockito.verify(commentAdaptor, Mockito.times(1)).getCommentsByTaskId(anyLong());

    }

    @Test
    void registerCommentByTaskId() {
        // Arrange
        Long taskId = 12345L;
        CommentRegisterDto registerDto = new CommentRegisterDto("User1", "Comment 1");
        Result expectedResult = new Result("ok");
        when(commentAdaptor.registerCommentByTaskId(anyLong(), any(CommentRegisterDto.class))).thenReturn(Optional.of(expectedResult));

        // Act
        Result result = commentService.registerCommentByTaskId(taskId, registerDto);

        // Assert
        assertEquals(expectedResult, result);
        Mockito.verify(commentAdaptor, Mockito.times(1)).registerCommentByTaskId(anyLong(), any(CommentRegisterDto.class));

    }
}