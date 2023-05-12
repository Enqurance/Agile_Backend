package com.example.backend.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Tpin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author DELL
 * @description 针对表【tpin】的数据库操作Mapper
 * @createDate 2023-05-12 10:50:37
 * @Entity com.example.backend.domain.Tpin
 */
public interface TpinMapper extends BaseMapper<Tpin> {
    int insertAll(Tpin tpin);

    int deleteById(@Param("id") Integer id);

    List<Tpin> findAll();

    List<Tpin> findAllByPId(@Param("pId") Integer pId);
}




