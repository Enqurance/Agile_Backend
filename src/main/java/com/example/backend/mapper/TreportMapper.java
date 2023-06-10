package com.example.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Treport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author DELL
 * @description 针对表【treport】的数据库操作Mapper
 * @createDate 2023-05-12 20:48:30
 * @Entity com.example.backend.domain.Treport
 */
public interface TreportMapper extends BaseMapper<Treport> {
    int insertAll(Treport treport);

    int deleteById(@Param("id") Integer id);

    List<Treport> findAllByOIdAndType(@Param("oId") Integer oId,
                                      @Param("type") Integer type);
}




