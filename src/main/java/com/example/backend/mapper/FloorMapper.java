package com.example.backend.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Floor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Sisyphus
* @description 针对表【floor】的数据库操作Mapper
* @createDate 2023-05-13 15:50:34
* @Entity com.example.backend.domain.Floor
*/
public interface FloorMapper extends BaseMapper<Floor> {
    int insertFloor(Floor floor);

    Floor getFloorById(Integer id);

    int deleteById(@Param("id") Integer id);

    int findMaxId();

    List<Floor> getFloorsOrderTime(Integer postId);

    List<Floor> getFloorIdByPostId(Integer postId);

    List<Floor> getMyAllFloor(Integer id);
}




