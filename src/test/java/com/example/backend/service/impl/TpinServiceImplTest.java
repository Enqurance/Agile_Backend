package com.example.backend.service.impl;

import com.example.backend.domain.Tpin;
import com.example.backend.mapper.TpinMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TpinServiceImplTest {
    @Mock
    TpinMapper tpinMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TpinServiceImpl tpinServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertTask() {
        when(tpinMapper.insertAll(any())).thenReturn(0);
        when(tpinMapper.findAllByPId(anyInt())).thenReturn(List.of());

        int result = tpinServiceImpl.insertTask(0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDeleteTask() {
        when(tpinMapper.deleteById(anyInt())).thenReturn(0);

        int result = tpinServiceImpl.deleteTask(0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDeletePin() {
        when(tpinMapper.deleteByPId(anyInt())).thenReturn(0);

        int result = tpinServiceImpl.deletePin(0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFindAllTasks() {
        when(tpinMapper.findAll()).thenReturn(List.of(new Tpin()));

        List<Tpin> result = tpinServiceImpl.findAllTasks();
        Assertions.assertEquals(List.of(new Tpin()), result);
    }

    @Test
    void testPinState() {
        when(tpinMapper.findAllByPId(anyInt())).thenReturn(List.of(new Tpin()));

        int result = tpinServiceImpl.pinState(0);
        Assertions.assertEquals(1, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme