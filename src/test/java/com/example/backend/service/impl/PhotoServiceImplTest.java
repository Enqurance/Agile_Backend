package com.example.backend.service.impl;

import com.example.backend.domain.Photo;
import com.example.backend.mapper.PhotoMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class PhotoServiceImplTest {
    @Mock
    PhotoMapper photoMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    PhotoServiceImpl photoServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertPhoto() {
        when(photoMapper.insertAll(any())).thenReturn(0);

        Integer result = photoServiceImpl.insertPhoto(new Photo());
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testGetPhotoUrlByPinId() {
        when(photoMapper.getPhotoUrlByPinId(anyInt())).thenReturn(new ArrayList<>(List.of("String")));

        ArrayList<String> result = photoServiceImpl.getPhotoUrlByPinId(Integer.valueOf(0));
        Assertions.assertEquals(new ArrayList<>(List.of("String")), result);
    }

    @Test
    void testGetUrlStrById() {
        when(photoMapper.getPhotoUrlByPinId(anyInt())).thenReturn(new ArrayList<>(List.of("String")));

        String result = photoServiceImpl.getUrlStrById(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testUpload() {
        String result = photoServiceImpl.Upload("prefix", null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testDelete() {
        photoServiceImpl.delete("path");
    }

    @Test
    void testDeletePhotoByPinId() {
        when(photoMapper.getPhotoUrlByPinId(anyInt())).thenReturn(new ArrayList<>(List.of("String")));
        when(photoMapper.deletePhotoByPinId(anyInt())).thenReturn(0);

        int result = photoServiceImpl.deletePhotoByPinId(Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testDeletePhotoByUrl() {
        when(photoMapper.deletePhotoByUrl(anyString(), anyInt())).thenReturn(0);

        int result = photoServiceImpl.deletePhotoByUrl("url", Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
