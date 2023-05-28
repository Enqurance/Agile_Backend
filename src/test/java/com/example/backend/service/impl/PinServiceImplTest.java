package com.example.backend.service.impl;

import com.example.backend.domain.Pin;
import com.example.backend.mapper.PinMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PinServiceImplTest {
    @Mock
    PinMapper pinMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    PinServiceImpl pinServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertPin() {
        when(pinMapper.insertAll(any())).thenReturn(0);

        int result = pinServiceImpl.insertPin(new Pin());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testSearchPin() {
        when(pinMapper.searchAll(anyString(), anyInt())).thenReturn(new ArrayList<>(List.of(new Pin())));

        ArrayList<Pin> result = pinServiceImpl.searchPin("searchContext", Integer.valueOf(0));
        Assertions.assertEquals(new ArrayList<>(List.of(new Pin())), result);
    }

    @Test
    void testUpdatePin() {
        when(pinMapper.updateAll(any())).thenReturn(0);

        int result = pinServiceImpl.updatePin(new Pin());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetPinById() {
        when(pinMapper.getPinById(anyInt())).thenReturn(new Pin());

        Pin result = pinServiceImpl.getPinById(Integer.valueOf(0));
        Assertions.assertEquals(new Pin(), result);
    }

    @Test
    void testFindMaxId() {
        when(pinMapper.findMaxId()).thenReturn(0);

        int result = pinServiceImpl.findMaxId();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDeletePinById() {
        when(pinMapper.deletePinById(anyInt())).thenReturn(0);

        int result = pinServiceImpl.deletePinById(Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetUserAllBriefPin() {
        when(pinMapper.getAllByUser_idOrVisibility(anyInt(), anyInt())).thenReturn(List.of(new Pin()));

        List<Pin> result = pinServiceImpl.getUserAllBriefPin(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(List.of(new Pin()), result);
    }

    @Test
    void testPinPublic() {
        when(pinMapper.updateVisibilityById(anyInt(), anyInt())).thenReturn(0);

        int result = pinServiceImpl.pinPublic(0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testSwitchPos() {
        when(pinMapper.switchPos(anyInt(), anyString())).thenReturn(0);

        int result = pinServiceImpl.switchPos(new Pin());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetMyAllPin() {
        when(pinMapper.getMyAllPin(anyInt())).thenReturn(List.of(new Pin()));

        List<Pin> result = pinServiceImpl.getMyAllPin(Integer.valueOf(0));
        Assertions.assertEquals(List.of(new Pin()), result);
    }

    @Test
    void testGetAllPublicPin() {
        when(pinMapper.getPublicPinByType(anyInt())).thenReturn(List.of(new Pin()));

        List<Pin> result = pinServiceImpl.getAllPublicPin(Integer.valueOf(0));
        Assertions.assertEquals(List.of(new Pin()), result);
    }

    @Test
    void testGetNameStrByIdStr() {
        when(pinMapper.getPinById(anyInt())).thenReturn(new Pin());

        String result = pinServiceImpl.getNameStrByIdStr("76;77;78");
        Assertions.assertEquals("null;null;null", result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
