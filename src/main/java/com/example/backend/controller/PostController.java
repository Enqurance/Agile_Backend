package com.example.backend.controller;

import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.entity.PostDetail;
import com.example.backend.entity.message.PostSearch;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/forum/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;

    @RequestMapping("/addPost")
    public CommonResult addPost(@RequestParam(name = "id") Integer id,
                                @RequestParam(value = "tag") Integer tag,
                                @RequestParam(value = "pinIdStr") String pinIdStr,
                                @RequestParam(value = "title") String title,
                                @RequestParam(value = "content") String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setVisibility(1);
        post.setTag(tag);
        post.setThumbsUp(0);
        post.setVisit(0);
        post.setFloorNum(0);
        post.setPinIdStr(pinIdStr);
        post.setCreateTime(new Date());
        post.setUserId(id);
        int ret = postService.addPost(post);
        if (ret == 0)
            return CommonResult.failed("post数据插入失败");
        else
            return CommonResult.success(postService.findMaxId());
    }

    @RequestMapping("/getPosts")
    public CommonResult getPosts(@RequestParam(value = "offset") Integer offset,
                                 @RequestParam(value = "limit") Integer limit) {
        List<Post> posts = postService.getPostsOrderTime(-1);
        if (posts.size() == 0)
            return CommonResult.failed("不存在满足条件的post");
        OrderPostByHot(posts);
        int cnt = 0;
        List<Post> retPosts = new ArrayList<>();
        for (int index = offset; index + cnt < posts.size() && cnt < limit; cnt++) {
            retPosts.add(posts.get(index + cnt));
        }
        return CommonResult.success(retPosts);
    }

    private void OrderPostByHot(List<Post> posts) {
        posts.sort((p1, p2) ->
                (p2.getThumbsUp() * 10 + p2.getVisit()) - (p1.getThumbsUp() * 10 + p1.getVisit()));
    }

    @RequestMapping("/getPostsByTag")
    public CommonResult getPostsByTag(@RequestParam(value = "offset") Integer offset,
                                      @RequestParam(value = "limit") Integer limit,
                                      @RequestParam(value = "type") Integer type) {
        List<Post> posts = postService.getPostsOrderTime(type);
        if (posts.size() == 0)
            return CommonResult.failed("不存在满足条件的post");
        List<Post> retPosts = new ArrayList<>();
        int cnt = 0;
        for (int index = offset; index + cnt < posts.size() && cnt <= limit; index++) {
            retPosts.add(posts.get(index + cnt));
            cnt++;
        }
        return CommonResult.success(retPosts);
    }

    @RequestMapping("/searchPosts")
    public CommonResult searchPosts(@RequestParam(value = "searchContext") String context) {
        List<Post> posts = postService.searchPosts(context);
        if (posts.size() == 0)
            return CommonResult.failed("不存在满足条件的post");
        List<PostSearch> postSearches = new ArrayList<>();
        for (Post post : posts) {
            PostSearch postSearch = new PostSearch(post.getId(), post.getTitle());
            postSearches.add(postSearch);
        }
        return CommonResult.success(postSearches);
    }

    @RequestMapping("/getPostDetail")
    public CommonResult getPostDetail(@RequestParam(name = "id") Integer id,
                                      @RequestParam(value = "post_id") Integer post_id) {
        Post post = postService.getPostById(post_id);
        if (post == null)
            return CommonResult.failed("不存在对应id的post");
        int is_auth = 0;
        if (Objects.equals(id, post.getId()))
            is_auth = 1;
        PostDetail postDetail = new PostDetail(post, is_auth);
        return CommonResult.success(postDetail);
    }

    @DeleteMapping("/deletePost/{post_id}")
    public CommonResult deletePost(@PathVariable(value = "post_id", required = false) Integer post_id) {
        List<Floor> floors = floorService.getFloorIdByPostId(post_id);
        for (Floor floor : floors) {
            int floorId = floor.getId();
            commentService.deleteCommentByFloorId(floorId);
            int retFloor = floorService.deleteFloorById(floorId);
            if (retFloor == 0)
                return CommonResult.failed("id = " + floorId + "的floor删除失败");
        }
        int ret = postService.deletePostById(post_id);
        if (ret == 0)
            return CommonResult.failed("对应post删除失败或post不存在");
        else
            return CommonResult.success("对应post删除成功");
    }

    @RequestMapping("/changePost")
    public CommonResult changePost(@RequestParam(value = "post_id") Integer post_id,
                                   @RequestParam(value = "tag") Integer tag,
                                   @RequestParam(value = "pinIdStr") String pinIdStr,
                                   @RequestParam(value = "title") String title,
                                   @RequestParam(value = "content") String content) {
        Post post = new Post();
        post.setId(post_id);
        post.setTitle(title);
        post.setContent(content);
        post.setTag(tag);
        post.setPinIdStr(pinIdStr);
        int ret = postService.updatePost(post);
        if (ret == 0)
            return CommonResult.failed("对应post更新失败或post不存在");
        else
            return CommonResult.success("对应post更新成功");
    }
}
