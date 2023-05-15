package com.example.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Tfeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author DELL
 * @description 针对表【tfeedback】的数据库操作Mapper
 * @createDate 2023-05-15 10:59:23
 * @Entity com.example.backend.domain.Tfeedback
 */
public interface TfeedbackMapper extends BaseMapper<Tfeedback> {
    int insertAll(Tfeedback tfeedback);

    int deleteById(@Param("id") Integer id);

    List<Tfeedback> findAllByPId(@Param("pId") Integer pId);

    List<Tfeedback> findAllByPIdAndUId(@Param("pId") Integer pId,
                                       @Param("uId") Integer uId);

    List<Tfeedback> findPId();
}




