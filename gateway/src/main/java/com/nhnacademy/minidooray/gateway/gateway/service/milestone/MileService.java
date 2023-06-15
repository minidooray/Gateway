package com.nhnacademy.minidooray.gateway.gateway.service.milestone;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MileService {
    List<MilestoneDto> getMilestonesByProjectId(Long projectId);
    Result registerMilestone(Long projectId, MilestoneRegisterDto milestoneRegisterDto);
}
