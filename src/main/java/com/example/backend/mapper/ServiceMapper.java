package com.example.backend.mapper;

import com.example.backend.domain.BuaaService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

/**
* @author Sisyphus
* @description 针对表【service】的数据库操作Mapper
* @createDate 2023-04-11 16:29:28
* @Entity com.example.backend.domain.Service
*/
public interface ServiceMapper extends BaseMapper<BuaaService> {
    int insertAll(BuaaService buaaService);

    BuaaService getServiceById(Integer id);
}




