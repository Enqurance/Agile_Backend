package com.example.backend.service;

import com.example.backend.domain.Tpost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author DELL
 * @description 针对表【tpost】的数据库操作Service
 * @createDate 2023-05-12 10:50:55
 */
public interface TpostService extends IService<Tpost> {
    int newReport(String reason, int post_id, int u_id);

    int finishReport(int tpost_id);
}
