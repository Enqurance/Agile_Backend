package com.example.backend.mapper;

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

    int deleteById(@Param("m_id") Integer m_id);

    int updateStatusById(@Param("m_id") Integer m_id,
                         @Param("m_status") Integer m_status);

    List<Message> getAllReceiveByUreceiveId(@Param("ureceive_id") Integer ureceive_id);

    List<Message> getAllSendByUreceiveId(@Param("ureceive_id") Integer ureceive_id);
}




