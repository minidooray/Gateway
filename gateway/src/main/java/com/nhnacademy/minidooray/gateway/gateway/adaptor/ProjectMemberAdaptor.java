package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.project.ProjectIdDto;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberAdaptor {
    Optional<List<ProjectIdDto>> getProjectIdByMemberId(String memberId);
}
