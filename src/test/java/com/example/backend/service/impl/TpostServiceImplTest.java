package com.example.backend.service.impl;

import com.example.backend.domain.Tpost;
import com.example.backend.mapper.TpostMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TpostServiceImplTest {
    @Mock
    TpostMapper tpostMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TpostServiceImpl tpostServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewReport() {
        when(tpostMapper.insertAll(any())).thenReturn(0);
        Tpost tt=new Tpost();
        tt.setUId("123");
        when(tpostMapper.findAllByPostId(anyInt())).thenReturn(List.of(tt));
        when(tpostMapper.updateReasonAndUIdByPostId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = tpostServiceImpl.newReport("reason", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testNewReport2() {
        when(tpostMapper.insertAll(any())).thenReturn(0);
        when(tpostMapper.findAllByPostId(anyInt())).thenReturn(List.of());
        when(tpostMapper.updateReasonAndUIdByPostId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = tpostServiceImpl.newReport("reason", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFinishReport() {
        when(tpostMapper.deleteById(anyInt())).thenReturn(0);

        int result = tpostServiceImpl.finishReport(0);
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme