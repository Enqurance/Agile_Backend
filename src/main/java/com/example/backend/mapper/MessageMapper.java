package com.example.backend.mapper;
import java.util.Collection;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【message】的数据库操作Mapper
 * @createDate 2023-05-09 15:00:28
 * @Entity com.example.backend.domain.Message
 */
public interface MessageMapper extends BaseMapper<Message> {
    int insertAll(Message message);

    int deleteById(@Param("id") Integer m_id);

    int deleteByIdAndUreceiveId(@Param("id") Integer id,
                                @Param("ureceiveId") Integer ureceiveId);

    int updateStatusById(@Param("id") Integer m_id,
                         @Param("status") Integer m_status);

    int updateStatusByIdAndUreceiveId(@Param("status") Integer status,
                                      @Param("id") Integer id,
                                      @Param("ureceiveId") Integer ureceiveId);

    List<Message> getAllByUreceiveIdAndTypeIn(@Param("ureceiveId") Integer ureceiveId,
                                              @Param("typeList") Collection<Integer> typeList);

    int deleteByUreceiveIdAndTypeIn(@Param("ureceiveId") Integer ureceiveId,
                                    @Param("typeList") Collection<Integer> typeList);

    int updateStatusByUreceiveIdAndTypeIn(@Param("status") Integer status,
                                          @Param("ureceiveId") Integer ureceiveId,
                                          @Param("typeList") Collection<Integer> typeList);
}




