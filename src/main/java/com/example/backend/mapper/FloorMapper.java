package com.example.backend.mapper;

import com.example.backend.domain.Floor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Sisyphus
* @description 针对表【floor】的数据库操作Mapper
* @createDate 2023-05-13 15:50:34
* @Entity com.example.backend.domain.Floor
*/
public interface FloorMapper extends BaseMapper<Floor> {
    int insertFloor(Floor floor);

    Floor getFloorById(Integer id);
}




