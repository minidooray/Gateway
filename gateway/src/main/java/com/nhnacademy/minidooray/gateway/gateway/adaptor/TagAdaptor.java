package com.nhnacademy.minidooray.gateway.gateway.adaptor;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;

import java.util.List;
import java.util.Optional;

public interface TagAdaptor {
    Optional<List<TagDto>> getTagsByProjectId(Long projectId);
    Optional<TagDto> getTag(Long tagId);
    Optional<Result> registerTag(Long projectId, TagRegisterDto tagRegisterDto);
}
