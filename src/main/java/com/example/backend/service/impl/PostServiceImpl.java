package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Post;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.mapper.PostMapper;
import com.example.backend.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sisyphus
 * @description 针对表【post】的数据库操作Service实现
 * @createDate 2023-05-11 23:35:30
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {
    @Resource
    PostMapper postMapper;

    @Resource
    private FloorService floorService;

    @Resource
    private ReportService reportService;

    @Resource
    private TexamineService texamineService;

    @Resource
    private ExamineService examineService;

    @Override
    public int addPost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public Post getPostById(Integer id) {
        return postMapper.getPostById(id);
    }

    @Override
    public List<Post> getPostsOrderTime(Integer type) {
        return postMapper.getPostsOrderTime(type);
    }

    @Override
    public List<Post> searchPosts(String context) {
        String sqlText = "%" + context + "%";
        return postMapper.searchPosts(sqlText);
    }

    @Override
    public int updatePost(Post post) {
        return postMapper.updatePost(post);
    }

    @Override
    public int deletePostById(Integer id) {
        examineService.delete("post", id.toString());
        // 删除所属楼层
        floorService.getFloorIdByPostId(id).forEach(
                floor -> floorService.deleteFloorById(floor.getId())
        );
        // 删除举报
        reportService.finishReport(id, FORUMTYPE.POST);
        // 删除整改
        texamineService.finishTaskExamine(id);

        return postMapper.deletePostById(id);
    }

    @Override
    public int setPostVisById(Integer id, Integer visibility) {
        return postMapper.setPostVisById(id, visibility);
    }

    @Override
    public int findMaxId() {
        return postMapper.findMaxId();
    }

    @Override
    public int setFloorNum(Integer postId, int layers) {
        return postMapper.setFloorNum(postId, layers);
    }

    @Override
    public List<Post> getMyAllPost(Integer id) {
        return postMapper.getMyAllPost(id);
    }
}




