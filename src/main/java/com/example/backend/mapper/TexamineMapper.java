package com.example.backend.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Texamine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author DELL
 * @description 针对表【texamine】的数据库操作Mapper
 * @createDate 2023-05-20 10:52:21
 * @Entity com.example.backend.domain.Texamine
 */
public interface TexamineMapper extends BaseMapper<Texamine> {
    int insertAll(Texamine texamine);

    int deleteByPostId(@Param("postId") Integer postId);

    int updateTitleAndContentAndBasisByPostId(@Param("title") String title,
                                              @Param("content") String content,
                                              @Param("basis") String basis,
                                              @Param("postId") Integer postId);

    List<Texamine> findAllReadyTasks();

    List<Texamine> getAllByPostId(@Param("postId") Integer postId);
}




