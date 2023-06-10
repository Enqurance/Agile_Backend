package com.example.backend.service;

import com.example.backend.domain.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.FORUMTYPE;

import java.util.List;

/**
* @author DELL
* @description 针对表【report】的数据库操作Service
* @createDate 2023-05-12 20:48:21
*/
public interface ReportService extends IService<Report> {
    int newReport(String reason, int o_id, FORUMTYPE type, int u_id);

    int finishReport(int o_id, FORUMTYPE type);

    List<Report> getAllTypeReports(FORUMTYPE type);

    List<Report> getAllReports(int o_id, FORUMTYPE type);
}
