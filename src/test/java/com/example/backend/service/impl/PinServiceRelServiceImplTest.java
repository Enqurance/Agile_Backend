package com.example.backend.service.impl;

import com.example.backend.domain.PinServiceRel;
import com.example.backend.mapper.PinServiceRelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PinServiceRelServiceImplTest {
    @Mock
    PinServiceRelMapper pinServiceRelMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    PinServiceRelServiceImpl pinServiceRelServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertPinServiceRel() {
        when(pinServiceRelMapper.insertAll(any())).thenReturn(0);

        int result = pinServiceRelServiceImpl.insertPinServiceRel(new PinServiceRel());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetServiceIdByPinId() {
        when(pinServiceRelMapper.getServiceIdByPinId(anyInt())).thenReturn(new ArrayList<>(List.of(Integer.valueOf(0))));

        ArrayList<Integer> result = pinServiceRelServiceImpl.getServiceIdByPinId(Integer.valueOf(0));
        Assertions.assertEquals(new ArrayList<>(List.of(Integer.valueOf(0))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
