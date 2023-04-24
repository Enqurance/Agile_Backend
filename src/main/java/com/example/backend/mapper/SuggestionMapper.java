package com.example.backend.mapper;

import com.example.backend.domain.Suggestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Sisyphus
* @description 针对表【suggestion】的数据库操作Mapper
* @createDate 2023-04-24 17:25:17
* @Entity com.example.backend.domain.Suggestion
*/
public interface SuggestionMapper extends BaseMapper<Suggestion> {
    int insertAll(Suggestion suggestion);
}




