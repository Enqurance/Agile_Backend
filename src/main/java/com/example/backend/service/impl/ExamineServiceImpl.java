package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Examine;
import com.example.backend.service.ExamineService;
import com.example.backend.mapper.ExamineMapper;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

/**
* @author Sisyphus
* @description 针对表【examine】的数据库操作Service实现
* @createDate 2023-05-23 21:33:18
*/
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine>
    implements ExamineService{

    @Override
    public int examineText(JsonObject jsonObject) {
        return 0;
    }
}




