package com.example.backend.service.impl;

import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.domain.Userthumb;
import com.example.backend.mapper.PostMapper;
import com.example.backend.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class PostServiceImplTest {
    @Mock
    PostMapper postMapper;
    @Mock
    FloorService floorService;
    @Mock
    ReportService reportService;
    @Mock
    TexamineService texamineService;
    @Mock
    ExamineService examineService;
    @Mock
    UserthumbService userthumbService;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    PostServiceImpl postServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPost() {
        when(postMapper.insertPost(any())).thenReturn(0);

        int result = postServiceImpl.addPost(new Post());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetPostById() {
        when(postMapper.getPostById(anyInt())).thenReturn(new Post());

        Post result = postServiceImpl.getPostById(Integer.valueOf(0));
        Assertions.assertEquals(new Post(), result);
    }

    @Test
    void testGetPostsOrderTime() {
        when(postMapper.getPostsOrderTime(anyInt())).thenReturn(List.of(new Post()));

        List<Post> result = postServiceImpl.getPostsOrderTime(Integer.valueOf(0));
        Assertions.assertEquals(List.of(new Post()), result);
    }

    @Test
    void testSearchPosts() {
        when(postMapper.searchPosts(anyString())).thenReturn(List.of(new Post()));

        List<Post> result = postServiceImpl.searchPosts("context");
        Assertions.assertEquals(List.of(new Post()), result);
    }

    @Test
    void testUpdatePost() {
        when(postMapper.updatePost(any())).thenReturn(0);

        int result = postServiceImpl.updatePost(new Post());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDeletePostById() {
        when(postMapper.deletePostById(anyInt())).thenReturn(0);
        when(floorService.deleteFloorById(anyInt())).thenReturn(0);
        when(floorService.getFloorIdByPostId(anyInt())).thenReturn(List.of(new Floor()));
        when(reportService.finishReport(anyInt(), any())).thenReturn(0);
        when(texamineService.finishTaskExamine(anyInt())).thenReturn(0);

        int result = postServiceImpl.deletePostById(Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testSetPostVisById() {
        when(postMapper.setPostVisById(anyInt(), anyInt())).thenReturn(0);

        postServiceImpl.setPostVisById(Integer.valueOf(0), Integer.valueOf(0));
    }

    @Test
    void testFindMaxId() {
        when(postMapper.findMaxId()).thenReturn(0);

        int result = postServiceImpl.findMaxId();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testSetFloorNum() {
        when(postMapper.setFloorNum(anyInt(), anyInt())).thenReturn(0);

        int result = postServiceImpl.setFloorNum(Integer.valueOf(0), 0);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetMyAllPost() {
        when(postMapper.getMyAllPost(anyInt())).thenReturn(List.of(new Post()));

        List<Post> result = postServiceImpl.getMyAllPost(Integer.valueOf(0));
        Assertions.assertEquals(List.of(new Post()), result);
    }

    @Test
    void testSetPostThumb() {
        when(postMapper.setPostThumb(anyInt(), anyInt())).thenReturn(0);

        int result = postServiceImpl.setPostThumb(Integer.valueOf(0), Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddLike1() {
        Post post = new Post();
        post.setThumbsUp(0);
        when(postMapper.getPostById(anyInt())).thenReturn(post);
        when(postMapper.setPostThumb(anyInt(), anyInt())).thenReturn(0);
        when(userthumbService.getThumbById(anyInt(), anyInt())).thenReturn(new Userthumb());
        when(userthumbService.insertThumb(any())).thenReturn(0);
        when(userthumbService.deleteThumbById(anyInt(), anyInt())).thenReturn(0);

        int result = postServiceImpl.addLike(Integer.valueOf(7), Integer.valueOf(76));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testAddLike2() {
        Post post = new Post();
        post.setThumbsUp(0);
        when(postMapper.getPostById(anyInt())).thenReturn(post);
        when(postMapper.setPostThumb(anyInt(), anyInt())).thenReturn(0);
        when(userthumbService.getThumbById(anyInt(), anyInt())).thenReturn(null);
        when(userthumbService.insertThumb(any())).thenReturn(0);
        when(userthumbService.deleteThumbById(anyInt(), anyInt())).thenReturn(0);

        int result = postServiceImpl.addLike(Integer.valueOf(7), Integer.valueOf(76));
        Assertions.assertEquals(1, result);
    }

    @Test
    void testAddVisit() {
        when(postMapper.addVisitById(anyInt())).thenReturn(0);

        int result = postServiceImpl.addVisit(0);
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
