package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.MilestoneAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MileStoneAdaptorImpl implements MilestoneAdaptor {
    private final RestTemplate restTemplate;
    @Override
    public Optional<List<MilestoneDto>> getMilestonesByProjectId(Long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<MilestoneDto>> exchange =
                restTemplate.exchange("http://localhost:8083/projects/{id}/milestones", HttpMethod.GET, entity, new ParameterizedTypeReference<List<MilestoneDto>>() {
                },projectId);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<Result> registerMilestone(Long projectId, MilestoneRegisterDto milestoneRegisterDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(milestoneRegisterDto,headers);
        ResponseEntity<Result> exchange =
                restTemplate.exchange("http://localhost:8083/projects/{id}/milestones", HttpMethod.POST, entity,Result.class,projectId);
        return Optional.of(exchange.getBody());
    }
}
