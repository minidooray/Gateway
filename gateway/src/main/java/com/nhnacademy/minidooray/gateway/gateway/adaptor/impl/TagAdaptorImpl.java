package com.nhnacademy.minidooray.gateway.gateway.adaptor.impl;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class TagAdaptorImpl implements TagAdaptor {
    private final RestTemplate restTemplate;
    @Override
    public Optional<List<TagDto>> getTagsByProjectId(Long projectId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<TagDto>> exchange =
                restTemplate.exchange("http://localhost:8082/projects/{id}/tags", HttpMethod.GET, entity, new ParameterizedTypeReference<List<TagDto>>() {
                },projectId);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<TagDto> getTag(Long tagId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<TagDto> exchange =
                restTemplate.exchange("http://localhost:8082/project/1/tags/{tagId}",HttpMethod.GET,entity, TagDto.class,tagId);
        return Optional.of(exchange.getBody());
    }

    @Override
    public Optional<Result> registerTag(Long projectId, TagRegisterDto tagRegisterDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(tagRegisterDto,headers);
        ResponseEntity<Result> exchange =
                restTemplate.exchange("http://localhost:8082/project/{projectId}/tags",HttpMethod.POST,entity, Result.class,projectId);
        return Optional.of(exchange.getBody());
    }
}
