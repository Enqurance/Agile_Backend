package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Suggestion;
import com.example.backend.service.SuggestionService;
import com.example.backend.mapper.SuggestionMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【suggestion】的数据库操作Service实现
* @createDate 2023-04-24 17:25:17
*/
@Service
public class SuggestionServiceImpl extends ServiceImpl<SuggestionMapper, Suggestion>
    implements SuggestionService{
    @Resource
    private SuggestionMapper suggestionMapper;

    @Override
    public int insertSuggestion(Suggestion suggestion) {
        return suggestionMapper.insertAll(suggestion);
    }
}




