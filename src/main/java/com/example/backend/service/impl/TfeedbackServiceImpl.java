package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Tfeedback;
import com.example.backend.mapper.TfeedbackMapper;
import com.example.backend.service.TfeedbackService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DELL
 * @description 针对表【tfeedback】的数据库操作Service实现
 * @createDate 2023-05-15 10:59:24
 */
@Service
public class TfeedbackServiceImpl extends ServiceImpl<TfeedbackMapper, Tfeedback>
        implements TfeedbackService {
    @Resource
    private TfeedbackMapper tfeedbackMapper;

    @Override
    public int newFeedback(String title, String content, int p_id, int u_id) {
        if (tfeedbackMapper.findAllByPIdAndUId(p_id, u_id).size() != 0) {
            throw new RuntimeException("已提交过反馈申请，请等待管理员审核~");
        }

        Tfeedback tfeedback = new Tfeedback();
        tfeedback.setTitle(title);
        tfeedback.setContent(content);
        tfeedback.setPId(p_id);
        tfeedback.setUId(u_id);

        return tfeedbackMapper.insertAll(tfeedback);
    }

    @Override
    public int finishFeedbacks(List<Integer> tfeedback_id_list, String info) {
        int result = 0;
        for (Integer id : tfeedback_id_list) {
            result += tfeedbackMapper.deleteById(id);
            // TODO 给反馈发送者发送消息
        }

        // 全部正常删除返回1，否则返回0
        return result == tfeedback_id_list.size() ? 1 : 0;
    }

    @Override
    public int hasFeedback(int p_id, int u_id) {
        return tfeedbackMapper.findAllByPIdAndUId(p_id, u_id).size();
    }

    @Override
    public List<Tfeedback> findAllPin() {
        return tfeedbackMapper.findPId();
    }

    @Override
    public List<Tfeedback> findAllPinFeedback(int p_id) {
        return tfeedbackMapper.findAllByPId(p_id);
    }
}




