package com.example.backend.service.impl;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.mapper.FloorMapper;
import com.example.backend.service.CommentService;
import com.example.backend.service.ExamineService;
import com.example.backend.service.ReportService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class FloorServiceImplTest {
    @Mock
    FloorMapper floorMapper;
    @Mock
    CommentService commentService;
    @Mock
    ReportService reportService;
    @Mock
    ExamineService examineService;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    FloorServiceImpl floorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFloor() {
        when(floorMapper.insertFloor(any())).thenReturn(1);

        int result = floorServiceImpl.addFloor(new Floor());
        Assertions.assertEquals(1, result);
    }

    @Test
    void testGetFloorById() {
        when(floorMapper.getFloorById(anyInt())).thenReturn(new Floor());

        Floor result = floorServiceImpl.getFloorById(0);
        Assertions.assertEquals(new Floor(), result);
    }

    @Test
    void testDeleteFloorById() {
        when(floorMapper.deleteById(anyInt())).thenReturn(1);
        when(commentService.deleteCommentById(anyInt())).thenReturn(1);
        when(commentService.getAllCommentByFloorId(anyInt())).thenReturn(List.of(new Comment()));
        when(reportService.finishReport(anyInt(), any())).thenReturn(1);

        int result = floorServiceImpl.deleteFloorById(0);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindMaxId() {
        when(floorMapper.findMaxId()).thenReturn(100);

        int result = floorServiceImpl.findMaxId();
        Assertions.assertEquals(100, result);
    }

    @Test
    void testGetFloorsOrderTime() {
        when(floorMapper.getFloorsOrderTime(anyInt())).thenReturn(List.of(new Floor()));

        List<Floor> result = floorServiceImpl.getFloorsOrderTime(0);
        Assertions.assertEquals(List.of(new Floor()), result);
    }

    @Test
    void testGetFloorIdByPostId() {
        when(floorMapper.getFloorIdByPostId(anyInt())).thenReturn(List.of(new Floor()));

        List<Floor> result = floorServiceImpl.getFloorIdByPostId(0);
        Assertions.assertEquals(List.of(new Floor()), result);
    }

    @Test
    void testGetMyAllFloor() {
        when(floorMapper.getMyAllFloor(anyInt())).thenReturn(List.of(new Floor()));

        List<Floor> result = floorServiceImpl.getMyAllFloor(0);
        Assertions.assertEquals(List.of(new Floor()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme