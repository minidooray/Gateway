package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.AccountAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.adaptor.impl.MileStoneAdaptorImpl;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MilestoneAdaptorTest {
    @Mock
    private RestTemplate restTemplate;

    private MilestoneAdaptor mileStoneAdaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mileStoneAdaptor  = new MileStoneAdaptorImpl(restTemplate);
    }
    @Test
    void getMilestonesByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<MilestoneDto> expectedMilestones = Arrays.asList(
                new MilestoneDto(1L,"Milestone 1",null,null),
                new MilestoneDto(2L,"Milestone 2",null,null)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<List<MilestoneDto>> response = new ResponseEntity<>(expectedMilestones, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<MilestoneDto>>>any(), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<List<MilestoneDto>> result = mileStoneAdaptor.getMilestonesByProjectId(projectId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedMilestones, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.GET), eq(entity), Mockito.<ParameterizedTypeReference<List<MilestoneDto>>>any(), eq(projectId));

    }

    @Test
    void registerMilestone() {
        // Arrange
        Long projectId = 1L;
        MilestoneRegisterDto milestoneRegisterDto = new MilestoneRegisterDto("Milestone",null,null);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<MilestoneRegisterDto> entity = new HttpEntity<>(milestoneRegisterDto, headers);
        Result expectedResult = new Result("ok");
        ResponseEntity<Result> response = new ResponseEntity<>(expectedResult, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId)))
                .thenReturn(response);

        // Act
        Optional<Result> result = mileStoneAdaptor.registerMilestone(projectId, milestoneRegisterDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedResult, result.get());
        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(anyString(), eq(HttpMethod.POST), eq(entity), eq(Result.class), eq(projectId));

    }
}