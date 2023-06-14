package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface MilestoneAdaptor {
    Optional<List<MilestoneDto>> getMilestonesByProjectId(Long projectId);
    Optional<Result> registerMilestone(Long projectId, MilestoneRegisterDto milestoneRegisterDto);
}
