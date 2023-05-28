package com.example.backend.service.impl;

import com.example.backend.domain.Post;
import com.example.backend.domain.Texamine;
import com.example.backend.mapper.PostMapper;
import com.example.backend.mapper.TexamineMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TexamineServiceImplTest {
    @Mock
    TexamineMapper texamineMapper;
    @Mock
    PostMapper postMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TexamineServiceImpl texamineServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewTaskExamine() {
        when(texamineMapper.insertAll(any())).thenReturn(0);

        int result = texamineServiceImpl.newTaskExamine(0, "basis");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFinishTaskExamine() {
        when(texamineMapper.deleteByPostId(anyInt())).thenReturn(0);

        int result = texamineServiceImpl.finishTaskExamine(0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetAllTasks() {
        when(texamineMapper.findAllReadyTasks()).thenReturn(List.of(new Texamine()));

        List<Texamine> result = texamineServiceImpl.getAllTasks();
        Assertions.assertEquals(List.of(new Texamine()), result);
    }

    @Test
    void testRectify() {
        when(texamineMapper.updateTitleAndContentByPostId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = texamineServiceImpl.rectify(0, "title", "content");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetTaskByPostId() {
        when(texamineMapper.getAllByPostId(anyInt())).thenReturn(List.of(new Texamine()));

        Texamine result = texamineServiceImpl.getTaskByPostId(0);
        Assertions.assertEquals(new Texamine(), result);
    }

    @Test
    void testGetUserExaminePosts() {
        when(postMapper.getUserExaminePosts(anyInt())).thenReturn(List.of(new Post()));

        List<Post> result = texamineServiceImpl.getUserExaminePosts(0);
        Assertions.assertEquals(List.of(new Post()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme