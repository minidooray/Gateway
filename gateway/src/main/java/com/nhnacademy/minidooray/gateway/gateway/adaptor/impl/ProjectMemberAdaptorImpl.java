package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectMemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectMemberAdaptorImpl implements ProjectMemberAdaptor {
    private final RestTemplate restTemplate;
    @Override
    public Optional<List<ProjectIdDto>> getProjectIdByMemberId(String memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<ProjectIdDto>> exchange = restTemplate
                .exchange("http://localhost:8082/projects/members/{id}", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProjectIdDto>>() {
        },memberId);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<Result> registerProjectMember(Long projectId, String memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<Result> exchange = restTemplate
                .exchange("http://localhost:8082/projects/{projectId}/members/{memberId}", HttpMethod.POST, entity, Result.class,projectId,memberId);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<List<ProjectMemberId>> getMemberIdByProjectId(Long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<ProjectMemberId>> exchange = restTemplate
                .exchange("http://localhost:8082/projects/{projectId}/members", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProjectMemberId>>() {
                }, projectId);
        return Optional.of(exchange.getBody());
    }
}
