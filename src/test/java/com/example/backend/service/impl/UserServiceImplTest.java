package com.example.backend.service.impl;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class UserServiceImplTest {
    @Mock
    UserMapper userMapper;

    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertUser() {
        when(userMapper.insertAll(any())).thenReturn(0);

        int result = userServiceImpl.insertUser(new User());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testFindUserByEmail() {
        when(userMapper.findAllByEmail(anyString())).thenReturn(List.of(new User()));

        List<User> result = userServiceImpl.findUserByEmail("email");
        Assertions.assertEquals(List.of(new User()), result);
    }

    @Test
    void testFindUserById() {
        when(userMapper.findAllById(anyInt())).thenReturn(List.of(new User()));

        List<User> result = userServiceImpl.findUserById(Integer.valueOf(0));
        Assertions.assertEquals(List.of(new User()), result);
    }

    @Test
    void testUpdateBasicInfo() {
        when(userMapper.updateNameAndCampusAndGradeAndGenderAndDescriptionById(anyString(), anyString(), anyString(), anyInt(), anyString(), anyInt())).thenReturn(0);

        int result = userServiceImpl.updateBasicInfo(new User());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdateIcon() {
        when(userMapper.updateIcon(anyString(), anyInt())).thenReturn(0);

        int result = userServiceImpl.updateIcon(new User());
        Assertions.assertEquals(0, result);
    }

    @Test
    void testUpdatePassword() {
        when(userMapper.updatePasswordById(anyString(), anyInt())).thenReturn(0);

        int result = userServiceImpl.updatePassword("password", Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetPassword() {
        User tt=new User();
        tt.setId(0);
        tt.setPassword("replaceMeWithExpectedResult");
        when(userMapper.getPasswordById(anyInt())).thenReturn(List.of(tt));

        String result = userServiceImpl.getPassword(Integer.valueOf(0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetType() {
        User tt=new User();
        tt.setType(0);
        when(userMapper.getTypeById(anyInt())).thenReturn(List.of(tt));

        int result = userServiceImpl.getType(Integer.valueOf(0));
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme