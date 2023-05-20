package com.example.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author DELL
 * @description 针对表【report】的数据库操作Mapper
 * @createDate 2023-05-12 20:48:21
 * @Entity com.example.backend.domain.Report
 */
public interface ReportMapper extends BaseMapper<Report> {
    int insertAll(Report report);

    int deleteByOIdAndType(@Param("oId") Integer oId,
                           @Param("type") Integer type);

    List<Report> getAllByType(@Param("type") Integer type);

    List<Report> getAllByOIdAndType(@Param("oId") Integer oId,
                                    @Param("type") Integer type);

    List<Report> findAllByTypeAndUId(@Param("type") Integer type,
                                     @Param("uId") Integer uId);
}




