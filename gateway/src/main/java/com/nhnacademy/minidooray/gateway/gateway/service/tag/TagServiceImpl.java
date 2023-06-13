package com.nhnacademy.minidooray.gateway.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
    private final TagAdaptor adaptor;
    @Override
    public List<TagDto> getTagsByProjectId(Long projectId) {
        return adaptor.getTagsByProjectId(projectId).get();
    }

    @Override
    public TagDto getTag(Long tagId) {
        return adaptor.getTag(tagId).get();
    }

    @Override
    public Result registerTag(Long projectId, TagRegisterDto tagRegisterDto) {
        return adaptor.registerTag(projectId,tagRegisterDto).get();
    }
}
