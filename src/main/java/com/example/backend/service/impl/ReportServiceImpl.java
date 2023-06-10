package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Report;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.ReportMapper;
import com.example.backend.service.ReportService;
import com.example.backend.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
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

    @Resource
    private RedisUtil redisUtil;

    @Value("${times.report}")
    private int maxTimes;

    private static String reportKey(int id) {
        return "report: " + id;
    }

    @Override
    public int newReport(String reason, int o_id, FORUMTYPE type, int u_id) {
        if (reportMapper.findAllByTypeAndOIdAndUId(type.getType(), o_id, u_id).size() != 0) {
            throw new RuntimeException("已发起过举报，请耐心等待管理员审核~");
        }

        String times;
        if ((times = redisUtil.get(reportKey(u_id))) == null) {
            // 为该用户添加最大举报次数，设置为第二天过期
            redisUtil.set(reportKey(u_id), String.valueOf(maxTimes - 1), RedisUtil.getNowToNextDaySeconds());
        } else {
            int t = Integer.parseInt(times);
            if (t > 0) {
                redisUtil.update(reportKey(u_id), String.valueOf(t - 1));
            } else {
                throw new RuntimeException("今日举报次数已用完");
            }
        }

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




