package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectStatusDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectAdaptorImpl implements ProjectAdaptor {
    private final RestTemplate restTemplate;
    @Override
    public Optional<List<ProjectDto>> getProjects() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<ProjectDto>> exchange = restTemplate.
                exchange("http://localhost:8082/projects", HttpMethod.GET, entity, new ParameterizedTypeReference<List<ProjectDto>>() {
        });
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<ProjectDto> getProjectById(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<ProjectDto> exchange = restTemplate.
                exchange("http://localhost:8082/projects/{projectId}",HttpMethod.GET,entity, ProjectDto.class,id);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<Result> registerProject(ProjectRegister projectRegister) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        Map<String,String> params = new HashMap<>();
        params.put("projectAdminId", projectRegister.getProjectAdminId());
        params.put("projectName", projectRegister.getProjectName());
        params.put("projectDescription", projectRegister.getProjectDescription());
        HttpEntity entity = new HttpEntity(projectRegister,headers);
        ResponseEntity<Result> exchange = restTemplate.exchange("http://localhost:8082/projects",HttpMethod.POST,entity,Result.class);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<Result> updateProject(Long id, ProjectStatusDto projectStatusDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(projectStatusDto,headers);
        ResponseEntity<Result> exchange = restTemplate.exchange("http://localhost:8082/project/{id}",HttpMethod.PATCH,entity,Result.class,id);
        return Optional.of(exchange.getBody());
    }
}
