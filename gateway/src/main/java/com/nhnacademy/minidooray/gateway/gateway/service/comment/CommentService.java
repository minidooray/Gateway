package com.nhnacademy.minidooray.gateway.gateway.service.comment;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CommentService {
    List<CommentDto> getCommentsByTaskId(Long taskId);
    Result registerCommentByTaskId(Long taskId, CommentRegisterDto registerDto);
}
