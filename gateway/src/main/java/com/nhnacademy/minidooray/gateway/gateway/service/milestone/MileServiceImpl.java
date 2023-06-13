package com.nhnacademy.minidooray.gateway.gateway.service.milestone;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.MilestoneAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.milestone.MilestoneRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MileServiceImpl implements MileService{
    private final MilestoneAdaptor adaptor;
    @Override
    public List<MilestoneDto> getMilestonesByProjectId(Long projectId) {
        return adaptor.getMilestonesByProjectId(projectId).get();
    }

    @Override
    public Result registerMilestone(Long projectId, MilestoneRegisterDto milestoneRegisterDto) {
        return adaptor.registerMilestone(projectId,milestoneRegisterDto).get();
    }
}
