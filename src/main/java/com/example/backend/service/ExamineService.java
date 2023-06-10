package com.example.backend.service;

import com.example.backend.domain.Examine;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.gson.JsonObject;

/**
* @author Sisyphus
* @description 针对表【examine】的数据库操作Service
* @createDate 2023-05-23 21:33:18
*/
public interface ExamineService extends IService<Examine> {
    void upload(String prefix, String context, String id);

    void delete(String prefix, String id);
}
