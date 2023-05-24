package com.example.backend.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Pin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.List;

/**
* @author Sisyphus
* @description 针对表【pin】的数据库操作Mapper
* @createDate 2023-04-09 16:23:05
* @Entity com.example.backend.domain.Pin
*/
public interface PinMapper extends BaseMapper<Pin> {
    int insertAll(Pin pin);

    ArrayList<Pin> searchAll(String searchContext, Integer id);

    int updateAll(Pin pin);

    Pin getPinById(Integer id);

    int findMaxId();

    int deletePinById(Integer id);

    List<Pin> getAllByUser_idOrVisibility(@Param("user_id") Integer user_id,
                                          @Param("visibility") Integer visibility);

    int updateVisibilityById(@Param("visibility") Integer visibility,
                             @Param("id") Integer id);

    int switchPos(Integer pinId, String lnglat);

    List<Pin> getMyAllPin(Integer id);

    List<Pin> getPublicPinByType(Integer type);
}




