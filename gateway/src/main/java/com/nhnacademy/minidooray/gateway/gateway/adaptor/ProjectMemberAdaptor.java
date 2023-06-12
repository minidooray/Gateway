package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectMemberId;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberAdaptor {
    Optional<List<ProjectIdDto>> getProjectIdByMemberId(String memberId);
    Optional<Result> registerProjectMember(Long projectId, String memberId);
    Optional<List<ProjectMemberId>> getMemberIdByProjectId(Long projectId);
}
