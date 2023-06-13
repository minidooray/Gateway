package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;

import java.util.List;
import java.util.Optional;

public interface MilestoneAdaptor {
    Optional<List<MilestoneDto>> getMilestonesByProjectId(Long projectId);
    Optional<Result> registerMilestone(Long projectId, MilestoneRegisterDto milestoneRegisterDto);
}
