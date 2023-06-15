package com.nhnacademy.minidooray.gateway.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<TagDto> getTagsByProjectId(Long projectId);
    TagDto getTag(Long tagId);

    Result registerTag(Long projectId, TagRegisterDto tagRegisterDto);
}
