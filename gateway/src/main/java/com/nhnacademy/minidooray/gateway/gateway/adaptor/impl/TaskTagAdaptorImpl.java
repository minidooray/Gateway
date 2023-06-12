package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskTagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskTagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskTagAdaptorImpl implements TaskTagAdaptor {
    private final RestTemplate restTemplate;
    @Override
    public Optional<Result> registerTaskTag(Long projectId,Long taskId, Long tagId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Result> exchange =
                restTemplate.exchange("http://localhost:8082/projects/{projectId}/tasks/{taskId}/tags/{tagId}", HttpMethod.POST,entity,Result.class,projectId,taskId,tagId);

        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<List<TaskTagDto>> getTagIdByTaskId(Long taskId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<TaskTagDto>> exchange =
                restTemplate.exchange("http://localhost:8082/projects/1/tasks/{taskId}/tags", HttpMethod.GET, entity, new ParameterizedTypeReference<List<TaskTagDto>>() {
                }, taskId);

        return Optional.of(exchange.getBody());
    }
}
