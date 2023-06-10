package com.example.backend.service;

import com.example.backend.domain.Treport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.FORUMTYPE;

/**
 * @author DELL
 * @description 针对表【treport】的数据库操作Service
 * @createDate 2023-05-12 20:48:30
 */
public interface TreportService extends IService<Treport> {
    int newReport(int o_id, FORUMTYPE type);

    int finishReport(int treport_id);
}
