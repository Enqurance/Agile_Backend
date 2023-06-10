package com.example.backend.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Tcomment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DELL
* @description 针对表【tcomment】的数据库操作Mapper
* @createDate 2023-05-12 10:51:19
* @Entity com.example.backend.domain.Tcomment
*/
public interface TcommentMapper extends BaseMapper<Tcomment> {
    int insertAll(Tcomment tcomment);

    int deleteById(@Param("id") Integer id);

    List<Tcomment> findAllByCommentId(@Param("commentId") Integer commentId);

    int updateReasonAndUIdByCommentId(@Param("reason") String reason,
                                      @Param("uId") String uId,
                                      @Param("commentId") Integer commentId);
}




