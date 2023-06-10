package com.example.backend.service.impl;

import com.example.backend.domain.BuaaService;
import com.example.backend.mapper.ServiceMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ServiceServiceImplTest {
    @Mock
    ServiceMapper serviceMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    ServiceServiceImpl serviceServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertService() {
        when(serviceMapper.insertAll(any())).thenReturn(0);

        int result = serviceServiceImpl.insertService(new BuaaService());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetServiceById() {
        when(serviceMapper.getServiceById(anyInt())).thenReturn(new BuaaService());

        BuaaService result = serviceServiceImpl.getServiceById(Integer.valueOf(0));
        Assertions.assertEquals(new BuaaService(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
