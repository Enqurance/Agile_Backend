package com.example.backend.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Tfloor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DELL
* @description 针对表【tfloor】的数据库操作Mapper
* @createDate 2023-05-12 10:51:09
* @Entity com.example.backend.domain.Tfloor
*/
public interface TfloorMapper extends BaseMapper<Tfloor> {
    int insertAll(Tfloor tfloor);

    int deleteById(@Param("id") Integer id);

    List<Tfloor> findAllByFloorId(@Param("floorId") Integer floorId);

    int updateReasonAndUIdByFloorId(@Param("reason") String reason,
                                    @Param("uId") String uId,
                                    @Param("floorId") Integer floorId);
}




