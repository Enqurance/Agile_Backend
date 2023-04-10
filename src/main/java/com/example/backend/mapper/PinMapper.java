package com.example.backend.mapper;

import com.example.backend.domain.Pin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.domain.User;

/**
* @author Sisyphus
* @description 针对表【pin】的数据库操作Mapper
* @createDate 2023-04-09 16:23:05
* @Entity com.example.backend.domain.Pin
*/
public interface PinMapper extends BaseMapper<Pin> {
    int insertAll(Pin pin);
}




