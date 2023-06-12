package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.task.TagDto;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface TagAdaptor {
    Optional<List<TagDto>> getTagsByProjectId(Long projectId);
    Optional<TagDto> getTag(Long tagId);
}
