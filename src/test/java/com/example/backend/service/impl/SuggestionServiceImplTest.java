package com.example.backend.service.impl;

import com.example.backend.domain.Suggestion;
import com.example.backend.mapper.SuggestionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class SuggestionServiceImplTest {
    @Mock
    SuggestionMapper suggestionMapper;
    //Field entityClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field mapperClass of type Class - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    SuggestionServiceImpl suggestionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertSuggestion() {
        when(suggestionMapper.insertAll(any())).thenReturn(0);

        int result = suggestionServiceImpl.insertSuggestion(new Suggestion());
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
