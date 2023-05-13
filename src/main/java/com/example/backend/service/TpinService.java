package com.example.backend.service;

import com.example.backend.domain.Tpin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author DELL
* @description 针对表【tpin】的数据库操作Service
* @createDate 2023-05-12 10:50:37
*/
public interface TpinService extends IService<Tpin> {
    int insertTask(int p_id);

    int deleteTask(int id);

    List<Tpin> findAllTasks();

    int pinState(int p_id);
}
