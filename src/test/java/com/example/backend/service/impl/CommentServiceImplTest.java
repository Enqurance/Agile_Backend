package com.example.backend.service.impl;

import com.example.backend.domain.Comment;
import com.example.backend.mapper.CommentMapper;
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

class CommentServiceImplTest {
    @Mock
    CommentMapper commentMapper;
    @Mock
    ReportService reportService;
    @Mock
    ExamineService examineService;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComment() {
        when(commentMapper.insertComment(any())).thenReturn(1);

        int result = commentServiceImpl.addComment(new Comment());
        Assertions.assertEquals(1, result);
    }

    @Test
    void testGetCommentById() {
        when(commentMapper.getCommentById(anyInt())).thenReturn(new Comment());

        Comment result = commentServiceImpl.getCommentById(0);
        Assertions.assertEquals(new Comment(), result);
    }

    @Test
    void testDeleteCommentById() {
        when(commentMapper.deleteById(anyInt())).thenReturn(1);
        when(reportService.finishReport(anyInt(), any())).thenReturn(1);

        int result = commentServiceImpl.deleteCommentById(0);
        Assertions.assertEquals(1, result);
    }

    @Test
    void testFindMaxId() {
        when(commentMapper.findMaxId()).thenReturn(100);

        int result = commentServiceImpl.findMaxId();
        Assertions.assertEquals(100, result);
    }

    @Test
    void testGetCommentsOrderTime() {
        when(commentMapper.getCommentsOrderTime(anyInt())).thenReturn(List.of(new Comment()));

        List<Comment> result = commentServiceImpl.getCommentsOrderTime(0);
        Assertions.assertEquals(List.of(new Comment()), result);
    }

    @Test
    void testGetMyAllComment() {
        when(commentMapper.getMyAllComment(anyInt())).thenReturn(List.of(new Comment()));

        List<Comment> result = commentServiceImpl.getMyAllComment(0);
        Assertions.assertEquals(List.of(new Comment()), result);
    }

    @Test
    void testGetAllCommentByFloorId() {
        when(commentMapper.getAllByFloorId(anyInt())).thenReturn(List.of(new Comment()));

        List<Comment> result = commentServiceImpl.getAllCommentByFloorId(0);
        Assertions.assertEquals(List.of(new Comment()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme