package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Post;
import com.example.backend.domain.User;
import com.example.backend.domain.Userthumb;
import com.example.backend.entity.ListPosts;
import com.example.backend.entity.message.LikeMessage;
import com.example.backend.entity.message.PostReleaseSuccessMessage;
import com.example.backend.entity.PostSearch;
import com.example.backend.result.CommonResult;
import com.example.backend.service.*;
import com.example.backend.utils.MessageUtil;
import com.example.backend.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/forum/post")
public class PostController {
    @Autowired
    ExamineService examineService;
    @Autowired
    PostService postService;
    @Autowired
    FloorService floorService;
    @Autowired
    CommentService commentService;
    @Autowired
    UserthumbService userthumbService;
    @Autowired
    PinService pinService;
    @Autowired
    UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/addPost")
    public CommonResult addPost(@RequestParam(name = "id") Integer id,
                                @RequestBody JSONObject object) {
        int tag = object.getInt("tag");
        String pinIdStr = object.getStr("pinIdStr");
        String title = object.getStr("title");
        String content = object.getStr("content");
        log.info(object.toString());
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
        if (ret == 0) {
            return CommonResult.failed("post数据插入失败");
        } else {
            int postId = postService.findMaxId();
            examineService.upload("post", post.getTitle() + ";"
                    + post.getContent(), Integer.toString(postId));

            MessageUtil.newMessage(new PostReleaseSuccessMessage(postId, id));

            return CommonResult.success(postId);
        }
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
        ListPosts listPosts = new ListPosts(retPosts, posts.size());
        return CommonResult.success(listPosts);
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
        ListPosts listPosts = new ListPosts(retPosts, posts.size());
        return CommonResult.success(listPosts);
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
    public CommonResult getPostDetail(@RequestParam(name = "id", required = false) Integer id,
                                      @RequestParam(value = "post_id") Integer post_id) {
        Post post = postService.getPostById(post_id);
        if (post == null)
            return CommonResult.failed("不存在对应id的post");
        int is_auth = 0;
        if (Objects.equals(id, post.getUserId()))
            is_auth = 1;
        post.setIs_auth(is_auth);
        int has_thumb = 0;
        Userthumb userthumb = userthumbService.getThumbById(id, post_id);
        List<User> users = userService.findUserById(post.getUserId());
        if (users.size() != 0)
            post.setUserName(users.get(0).getName());
        if (userthumb != null)
            has_thumb = 1;
        post.setHas_thumb(has_thumb);
        String pinNameStr = pinService.getNameStrByIdStr(post.getPinIdStr());
        post.setPinNameStr(pinNameStr);

        // 每个用户对每个帖子的访问量30s刷新一次，redis里键值格式为"u_id;post_id"
        if (id != null) {
            if (redisUtil.get("visit: " + id + ";" + post_id) == null) {
                redisUtil.set("visit: " + id + ";" + post_id, "1", 30);
                postService.addVisit(post_id);
            }
        }

        return CommonResult.success(post);
    }

    @DeleteMapping("/deletePost/{post_id}")
    public CommonResult deletePost(@PathVariable(value = "post_id", required = false) Integer post_id) {
        if (postService.deletePostById(post_id) == 0)
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

    @GetMapping("/addLike/{post_id}")
    public CommonResult addLike(@RequestParam(required = false, name = "id") Integer id,
                                @PathVariable(value = "post_id") Integer post_id) {
        int ret = postService.addLike(id, post_id);
        if (ret == 0) {
            return CommonResult.success("用户取消点赞帖子");
        } else {
            MessageUtil.newMessage(new LikeMessage(post_id, id, postService.getPostById(post_id).getUserId()));

            return CommonResult.success("用户点赞帖子成功");
        }
    }

    @RequestMapping("/getPostsByPinId")
    public CommonResult getPostsByPinId(@RequestParam(value = "pin_id") Integer pin_id) {
        List<Post> posts = postService.getPostsOrderTime(-1);
        List<Post> retPosts = new ArrayList<>();
        for (Post post : posts) {
            String[] ids = post.getPinIdStr().split(";");
            for (String id : ids) {
                if (Objects.equals(id, pin_id.toString())) {
                    retPosts.add(post);
                    break;
                }
            }
            if (retPosts.size() >= 2)
                break;
        }
        return CommonResult.success(retPosts);
    }
}
