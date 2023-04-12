package com.example.backend.mapper;

import com.example.backend.domain.PinServiceRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;

/**
* @author Sisyphus
* @description 针对表【pin_service】的数据库操作Mapper
* @createDate 2023-04-11 21:34:22
* @Entity com.example.backend.domain.PinService
*/
public interface PinServiceRelMapper extends BaseMapper<PinServiceRel> {
    int insertAll(PinServiceRel pinServiceRel);

    ArrayList<Integer> getServiceIdByPinId(Integer pinId);
}




