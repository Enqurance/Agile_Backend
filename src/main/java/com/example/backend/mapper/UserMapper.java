package com.example.backend.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.backend.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DELL
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-11 09:17:21
* @Entity com.example.backend.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    int insertAll(User user);

    List<User> findAllByEmail(@Param("email") String email);

    List<User> findAllById(@Param("id") Integer id);
}




