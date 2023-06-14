package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.CommentAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.comment.CommentRegisterDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommentAdaptorImpl implements CommentAdaptor {
    private final RestTemplate restTemplate;
    @Override
    public Optional<List<CommentDto>> getCommentsByTaskId(Long taskId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<CommentDto>> exchange =
                restTemplate.exchange("http://localhost:8082/projects/{taskId}/tasks/1/comments", HttpMethod.GET, entity, new ParameterizedTypeReference<List<CommentDto>>() {
                },taskId);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<Result> registerCommentByTaskId(Long taskId, CommentRegisterDto commentRegisterDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(commentRegisterDto,headers);
        ResponseEntity<Result> exchange =
                restTemplate.exchange("http://localhost:8082/projects/1/tasks/{taskId}/comments", HttpMethod.POST, entity, Result.class,taskId);
        return Optional.of(exchange.getBody());
    }
}
