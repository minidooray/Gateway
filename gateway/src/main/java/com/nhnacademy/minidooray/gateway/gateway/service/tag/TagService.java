package com.nhnacademy.minidooray.gateway.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.gateway.domain.task.TagDto;

import java.util.List;
import java.util.Optional;

public interface TagService {
    List<TagDto> getTagsByProjectId(Long projectId);
    TagDto getTag(Long tagId);
}
