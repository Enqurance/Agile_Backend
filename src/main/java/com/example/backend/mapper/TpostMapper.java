package com.example.backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Tpost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author DELL
 * @description 针对表【tpost】的数据库操作Mapper
 * @createDate 2023-05-12 10:50:55
 * @Entity com.example.backend.domain.Tpost
 */
public interface TpostMapper extends BaseMapper<Tpost> {
    int insertAll(Tpost tpost);

    int deleteById(@Param("id") Integer id);

    List<Tpost> findAllByPostId(@Param("postId") Integer postId);

    int updateReasonAndUIdByPostId(@Param("reason") String reason,
                                   @Param("uId") String uId,
                                   @Param("postId") Integer postId);
}




