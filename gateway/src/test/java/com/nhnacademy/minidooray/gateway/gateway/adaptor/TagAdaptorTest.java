package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.TagAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TagAdaptorTest {
    @Mock
    private RestTemplate restTemplate;

    private TagAdaptor tagAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tagAdaptor = new TagAdaptorImpl(restTemplate);
    }

    @Test
    void getTagsByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<TagDto> expectedTags = Arrays.asList(
                new TagDto(1L, "Tag 1"),
                new TagDto(2L, "Tag 2")
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<TagDto>> response = new ResponseEntity<>(expectedTags, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<TagDto>>>any(), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<List<TagDto>> result = tagAdaptor.getTagsByProjectId(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedTags, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<TagDto>>>any(), eq(projectId));

    }

    @Test
    void getTag() {
        // Arrange
        Long tagId = 1L;
        TagDto expectedTag = new TagDto(tagId, "Tag 1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<TagDto> response = new ResponseEntity<>(expectedTag, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(TagDto.class), eq(tagId)))
                .thenReturn(response);

        // Act
        Optional<TagDto> result = tagAdaptor.getTag(tagId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedTag, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), eq(TagDto.class), eq(tagId));

    }

    @Test
    void registerTag() {
        // Arrange
        Long projectId = 1L;
        TagRegisterDto tagRegisterDto = new TagRegisterDto("Tag 1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(tagRegisterDto, headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<Result> result = tagAdaptor.registerTag(projectId, tagRegisterDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResult, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId));

    }
}