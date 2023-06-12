package com.nhnacademy.minidooray.gateway.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.task.TagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
