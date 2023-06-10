package com.example.backend.service;

import com.example.backend.domain.Tcomment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author DELL
* @description 针对表【tcomment】的数据库操作Service
* @createDate 2023-05-12 10:51:19
*/
public interface TcommentService extends IService<Tcomment> {
    int newReport(String reason, int comment_id, int u_id);

    int finishReport(int tcomment_id);
}
