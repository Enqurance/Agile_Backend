package com.example.backend.service;

import com.example.backend.domain.Suggestion;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Sisyphus
* @description 针对表【suggestion】的数据库操作Service
* @createDate 2023-04-24 17:25:17
*/
public interface SuggestionService extends IService<Suggestion> {
    int insertSuggestion(Suggestion suggestion);
}
