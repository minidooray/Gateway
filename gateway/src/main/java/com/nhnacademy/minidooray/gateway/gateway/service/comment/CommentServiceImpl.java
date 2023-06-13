package com.nhnacademy.minidooray.gateway.gateway.service.comment;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.CommentAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentAdaptor adaptor;
    @Override
    public List<CommentDto> getCommentsByTaskId(Long taskId) {
        return adaptor.getCommentsByTaskId(taskId).get();
    }

    @Override
    public Result registerCommentByTaskId(Long taskId, CommentRegisterDto registerDto) {
        return adaptor.registerCommentByTaskId(taskId,registerDto).get();
    }
}
