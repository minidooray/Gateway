package com.nhnacademy.minidooray.gateway.gateway.service.milestone;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.MilestoneRegisterDto;

import java.util.List;
import java.util.Optional;

public interface MileService {
    List<MilestoneDto> getMilestonesByProjectId(Long projectId);
    Result registerMilestone(Long projectId, MilestoneRegisterDto milestoneRegisterDto);
}
