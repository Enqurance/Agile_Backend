package com.example.backend.service.impl;

import com.example.backend.domain.Report;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.ReportMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class ReportServiceImplTest {
    @Mock
    ReportMapper reportMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    ReportServiceImpl reportServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testNewReport() {
        when(reportMapper.insertAll(any())).thenReturn(1);
        when(reportMapper.findAllByTypeAndOIdAndUId(anyInt(), anyInt(), anyInt())).thenReturn(List.of());

        int result = reportServiceImpl.newReport("reason", 0, FORUMTYPE.POST, 0);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFinishReport() {
        when(reportMapper.deleteByOIdAndType(anyInt(), anyInt())).thenReturn(1);

        int result = reportServiceImpl.finishReport(0, FORUMTYPE.POST);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testGetAllTypeReports() {
        when(reportMapper.getAllByType(anyInt())).thenReturn(List.of(new Report()));

        List<Report> result = reportServiceImpl.getAllTypeReports(FORUMTYPE.POST);
        Assertions.assertEquals(List.of(new Report()), result);
    }

    @Test
    void testGetAllReports() {
        when(reportMapper.getAllByOIdAndType(anyInt(), anyInt())).thenReturn(List.of(new Report()));

        List<Report> result = reportServiceImpl.getAllReports(0, FORUMTYPE.POST);
        Assertions.assertEquals(List.of(new Report()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme