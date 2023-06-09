package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TaskAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TaskAdaptorImpl implements TaskAdaptor {
    private final RestTemplate restTemplate;


    @Override
    public Optional<List<TaskDto>> getTaskByProjectId(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<TaskDto>> exchange = restTemplate.exchange("http://localhost:8082/projects/{id}/tasks", HttpMethod.GET, entity, new ParameterizedTypeReference<List<TaskDto>>() {
        },id);
        return Optional.of(exchange.getBody());
    }
}
