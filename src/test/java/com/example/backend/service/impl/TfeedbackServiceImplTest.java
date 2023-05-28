package com.example.backend.service.impl;

import com.example.backend.domain.Tfeedback;
import com.example.backend.mapper.TfeedbackMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TfeedbackServiceImplTest {
    @Mock
    TfeedbackMapper tfeedbackMapper;

    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    TfeedbackServiceImpl tfeedbackServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewFeedback() {
        when(tfeedbackMapper.insertAll(any())).thenReturn(0);
        when(tfeedbackMapper.findAllByPIdAndUId(anyInt(), anyInt())).thenReturn(List.of());

        int result = tfeedbackServiceImpl.newFeedback("title", "content", 0, 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFinishFeedbacks() {
        when(tfeedbackMapper.deleteById(anyInt())).thenReturn(0);

        int result = tfeedbackServiceImpl.finishFeedbacks(List.of(Integer.valueOf(0)), "info");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testHasFeedback() {
        when(tfeedbackMapper.findAllByPIdAndUId(anyInt(), anyInt())).thenReturn(List.of(new Tfeedback()));

        int result = tfeedbackServiceImpl.hasFeedback(0, 0);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindAllPin() {
        when(tfeedbackMapper.findPId()).thenReturn(List.of(new Tfeedback()));

        List<Tfeedback> result = tfeedbackServiceImpl.findAllPin();
        Assertions.assertEquals(List.of(new Tfeedback()), result);
    }

    @Test
    void testFindAllPinFeedback() {
        when(tfeedbackMapper.findAllByPId(anyInt())).thenReturn(List.of(new Tfeedback()));

        List<Tfeedback> result = tfeedbackServiceImpl.findAllPinFeedback(0);
        Assertions.assertEquals(List.of(new Tfeedback()), result);
    }

    @Test
    void testFindFeedbackById() {
        when(tfeedbackMapper.findAllById(anyInt())).thenReturn(List.of(new Tfeedback()));

        Tfeedback result = tfeedbackServiceImpl.findFeedbackById(0);
        Assertions.assertEquals(new Tfeedback(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme