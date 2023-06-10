package com.example.backend.service.impl;

import com.example.backend.domain.Tcomment;
import com.example.backend.mapper.TcommentMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class TcommentServiceImplTest {
    @Mock
    TcommentMapper tcommentMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TcommentServiceImpl tcommentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewReport1() {
        when(tcommentMapper.insertAll(any())).thenReturn(0);
        when(tcommentMapper.findAllByCommentId(anyInt())).thenReturn(new ArrayList<>());
        when(tcommentMapper.updateReasonAndUIdByCommentId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = tcommentServiceImpl.newReport("reason", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testNewReport2() {
        Tcomment tcomment = new Tcomment();
        tcomment.setUId("7");
        tcomment.setReason("Reason");
        tcomment.setId(7);
        when(tcommentMapper.insertAll(any())).thenReturn(0);
        when(tcommentMapper.findAllByCommentId(anyInt())).thenReturn(List.of(tcomment));
        when(tcommentMapper.updateReasonAndUIdByCommentId(anyString(), anyString(), anyInt())).thenReturn(0);

        int result = tcommentServiceImpl.newReport("reason", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFinishReport() {
        when(tcommentMapper.deleteById(anyInt())).thenReturn(0);

        int result = tcommentServiceImpl.finishReport(0);
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
