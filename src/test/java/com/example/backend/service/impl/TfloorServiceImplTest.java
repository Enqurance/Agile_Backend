package com.example.backend.service.impl;

import com.example.backend.domain.Tfloor;
import com.example.backend.mapper.TfloorMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TfloorServiceImplTest {
    @Mock
    TfloorMapper tfloorMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TfloorServiceImpl tfloorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewReport() {
        when(tfloorMapper.insertAll(any())).thenReturn(0);
        Tfloor tt=new Tfloor();
        tt.setUId("1;2;3");
        when(tfloorMapper.findAllByFloorId(anyInt())).thenReturn(List.of(tt));
        when(tfloorMapper.updateReasonAndUIdByFloorId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = tfloorServiceImpl.newReport("reason", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testNewReport2() {
        when(tfloorMapper.insertAll(any())).thenReturn(0);
        when(tfloorMapper.findAllByFloorId(anyInt())).thenReturn(List.of());
        when(tfloorMapper.updateReasonAndUIdByFloorId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = tfloorServiceImpl.newReport("reason", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFinishReport() {
        when(tfloorMapper.deleteById(anyInt())).thenReturn(0);

        int result = tfloorServiceImpl.finishReport(0);
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme