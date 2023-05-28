package com.example.backend.service;

import com.example.backend.domain.Post;
import com.example.backend.domain.Texamine;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author DELL
* @description 针对表【texamine】的数据库操作Service
* @createDate 2023-05-20 10:52:21
*/
public interface TexamineService extends IService<Texamine> {
    int newTaskExamine(int post_id, String basis);

    int finishTaskExamine(int post_id);

    List<Texamine> getAllTasks();

    int rectify(int post_id, String title, String content, String basis);

    Texamine getTaskByPostId(int post_id);

    List<Post> getUserExaminePosts(int u_id);
}
