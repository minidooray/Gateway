package com.nhnacademy.minidooray.gateway.gateway.service.tag;

import com.nhnacademy.minidooray.gateway.gateway.adaptor.TagAdaptor;
import com.nhnacademy.minidooray.gateway.gateway.domain.Result;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagDto;
import com.nhnacademy.minidooray.gateway.gateway.domain.tag.TagRegisterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class TagServiceTest {
    @Mock
    private TagAdaptor tagAdaptor;


    private TagService tagService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        tagService = new TagServiceImpl(tagAdaptor);
    }
    @Test
    void getTagsByProjectId() {
        // Arrange
        Long projectId = 1L;
        List<TagDto> expectedTags = Arrays.asList(
                new TagDto(1L, "Tag 1"),
                new TagDto(2L, "Tag 2")
        );
        when(tagAdaptor.getTagsByProjectId(anyLong())).thenReturn(Optional.of(expectedTags));

        // Act
        List<TagDto> result = tagService.getTagsByProjectId(projectId);

        // Assert
        assertEquals(expectedTags, result);
    }

    @Test
    void getTag() {
        // Arrange
        Long tagId = 1L;
        TagDto expectedTag = new TagDto(tagId, "Tag 1");
        when(tagAdaptor.getTag(anyLong())).thenReturn(Optional.of(expectedTag));

        // Act
        TagDto result = tagService.getTag(tagId);

        // Assert
        assertEquals(expectedTag, result);
    }

    @Test
    void registerTag() {
        // Arrange
        Long projectId = 1L;
        TagRegisterDto registerDto = new TagRegisterDto("New Tag");
        Result expectedResult = new Result("ok");
        when(tagAdaptor.registerTag(anyLong(), any(TagRegisterDto.class))).thenReturn(Optional.of(expectedResult));

        // Act
        Result result = tagService.registerTag(projectId, registerDto);

        // Assert
        assertEquals(expectedResult, result);
    }
}