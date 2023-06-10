package com.example.backend.service.impl;

import com.example.backend.domain.Userthumb;
import com.example.backend.mapper.UserthumbMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UserthumbServiceImplTest {
    @Mock
    UserthumbMapper userthumbMapper;

    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    UserthumbServiceImpl userthumbServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetThumbById() {
        when(userthumbMapper.getThumbById(anyInt(), anyInt())).thenReturn(new Userthumb());

        Userthumb result = userthumbServiceImpl.getThumbById(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(new Userthumb(), result);
    }

    @Test
    void testInsertThumb() {
        when(userthumbMapper.insertThumb(any())).thenReturn(0);

        int result = userthumbServiceImpl.insertThumb(new Userthumb());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDeleteThumbById() {
        when(userthumbMapper.deleteThumbById(anyInt(), anyInt())).thenReturn(0);

        int result = userthumbServiceImpl.deleteThumbById(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme