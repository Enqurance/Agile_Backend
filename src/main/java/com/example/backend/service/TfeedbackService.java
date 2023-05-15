package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.domain.Tfeedback;

import java.util.List;

/**
* @author DELL
* @description 针对表【tfeedback】的数据库操作Service
* @createDate 2023-05-15 10:59:24
*/
public interface TfeedbackService extends IService<Tfeedback> {
    int newFeedback(String title, String content, int p_id, int u_id);

    int finishFeedbacks(List<Integer> tfeedback_id_list, String info);

    int hasFeedback(int p_id, int u_id);

    List<Tfeedback> findAllPin();

    List<Tfeedback> findAllPinFeedback(int p_id);
}
