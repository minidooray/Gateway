package com.nhnacademy.minidooray.gateway.gateway.service.projectMember;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.ProjectMemberAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectMemberId;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectRegister;
import com.nhnacademy.minidooray.gateway.gateway.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService{
    private final ProjectMemberAdaptor adaptor;
    @Override
    public List<ProjectIdDto> getProjectIdsByMemberId(String memberId) {
        return adaptor.getProjectIdByMemberId(memberId).get();
    }

    @Override
    public Result registerProject(Long projectId, String memberId) {
        return adaptor.registerProjectMember(projectId,memberId).get();
    }

    @Override
    public List<ProjectMemberId> getMemberIdsByProjectId(Long projectId) {
        return adaptor.getMemberIdByProjectId(projectId).get();
    }


}
