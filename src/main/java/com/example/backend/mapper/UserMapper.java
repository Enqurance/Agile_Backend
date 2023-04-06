package com.example.backend.mapper;
import org.apache.ibatis.annotations.Param;

import com.example.backend.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author DELL
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-04-06 08:40:06
* @Entity com.example.backend.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {
    int insertAll(User user);
}




