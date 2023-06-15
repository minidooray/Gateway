package com.nhnacademy.minidooray.gateway.gateway.adaptor;


import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface CommentAdaptor {
    Optional<List<CommentDto>> getCommentsByTaskId(Long taskId);
    Optional<Result> registerCommentByTaskId(Long taskId, CommentRegisterDto commentRegisterDto);
    Optional<Result> deleteComment(Long commentId);
    Optional<Result> updateComment(Long commentId, CommentRegisterDto commentRegisterDto);
}
