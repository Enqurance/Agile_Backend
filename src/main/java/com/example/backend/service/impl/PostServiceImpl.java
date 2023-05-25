package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Post;
import com.example.backend.domain.Userthumb;
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

    @Resource
    private UserthumbService userthumbService;

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
    public void setPostVisById(Integer id, Integer visibility) {
        postMapper.setPostVisById(id, visibility);
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

    @Override
    public int setPostThumb(Integer post_id, Integer thumbNum) {
        return postMapper.setPostThumb(post_id, thumbNum);
    }

    @Override
    public int addLike(Integer user_id, Integer post_id) {
        Userthumb userthumb = userthumbService.getThumbById(user_id, post_id);
        Post post = this.getPostById(post_id);
        if (userthumb == null) {
            Userthumb userthumbNew = new Userthumb();
            userthumbNew.setUserId(user_id);
            userthumbNew.setPostId(post_id);
            userthumbService.insertThumb(userthumbNew);
            this.setPostThumb(post_id, post.getThumbsUp() + 1);
            return 1;
        } else {
            userthumbService.deleteThumbById(user_id, post_id);
            this.setPostThumb(post_id, post.getThumbsUp() - 1);
            return 0;
        }
    }

    @Override
    public int addVisit(int post_id) {
        return postMapper.addVisitById(post_id);
    }
}




