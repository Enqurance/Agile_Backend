package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Report;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.ReportMapper;
import com.example.backend.service.ReportService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【report】的数据库操作Service实现
 * @createDate 2023-05-12 20:48:21
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report>
        implements ReportService {
    @Resource
    private ReportMapper reportMapper;

    @Override
    public int newReport(String reason, int o_id, FORUMTYPE type, int u_id) {
        Report report = new Report();
        report.setReason(reason);
        report.setOId(o_id);
        report.setType(type.getType());
        report.setUId(u_id);
        return reportMapper.insertAll(report);
    }

    @Override
    public int finishReport(int o_id, FORUMTYPE type) {
        return reportMapper.deleteByOIdAndType(o_id, type.getType());
    }

    @Override
    public List<Report> getAllTypeReports(FORUMTYPE type) {
        return reportMapper.getAllByType(type.getType());
    }

    @Override
    public List<Report> getAllReports(int o_id, FORUMTYPE type) {
        return reportMapper.getAllByOIdAndType(o_id, type.getType());
    }
}




