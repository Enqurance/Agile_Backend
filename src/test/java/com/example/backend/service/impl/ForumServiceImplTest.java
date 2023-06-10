package com.example.backend.service.impl;

import com.example.backend.domain.Forum;
import com.example.backend.mapper.ForumMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ForumServiceImplTest {
    @Mock
    ForumMapper forumMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    ForumServiceImpl forumServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertForum() {
        when(forumMapper.insertAll(any())).thenReturn(1);

        int result = forumServiceImpl.insertForum(new Forum());
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindMaxId() {
        when(forumMapper.findMaxId()).thenReturn(100);

        int result = forumServiceImpl.findMaxId();
        Assertions.assertEquals(100, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme