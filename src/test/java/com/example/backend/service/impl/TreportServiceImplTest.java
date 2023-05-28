package com.example.backend.service.impl;

import com.example.backend.domain.Treport;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.TreportMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TreportServiceImplTest {
    @Mock
    TreportMapper treportMapper;

    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TreportServiceImpl treportServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewReport() {
        when(treportMapper.insertAll(any())).thenReturn(0);
        when(treportMapper.findAllByOIdAndType(anyInt(), anyInt())).thenReturn(List.of(new Treport()));

        int result = treportServiceImpl.newReport(0, FORUMTYPE.POST);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFinishReport() {
        int result = treportServiceImpl.finishReport(0);
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme