package com.example.backend.controller;

import cn.hutool.json.JSONObject;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.domain.Report;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.entity.FrontendReply;
import com.example.backend.entity.FrontendReportPost;
import com.example.backend.entity.message.*;
import com.example.backend.result.CommonResult;
import com.example.backend.service.*;
import com.example.backend.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: ReportController
 * @Description: 针对举报信息提供api
 * @author: WAN
 * @date: 2023/5/18 21:07
 */
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private PostService postService;

    @Autowired
    private FloorService floorService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TexamineService texamineService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/examine/report/get_posts")
    public CommonResult getReportPosts() {
        Map<Integer, FrontendReportPost> posts = new HashMap<>();
        List<Report> reports = reportService.getAllTypeReports(FORUMTYPE.POST);
        for (Report report : reports) {
            if (posts.containsKey(report.getOId())) {
                posts.get(report.getOId()).addReason(report.getReason());
            } else {
                Post post = postService.getPostById(report.getOId());
                FrontendReportPost frontendReportPost = new FrontendReportPost(post);
                frontendReportPost.addReason(report.getReason());
                posts.put(report.getOId(), frontendReportPost);
            }
        }
        return CommonResult.success(posts.values());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/examine/report/result_of_report_post/{o_id}")
    public CommonResult finishReportPost(@PathVariable Integer o_id,
                                         @RequestBody JSONObject jsonObject) {
        /*
         * 0: 举报失败
         * 1: 整改帖子
         * 2: 删除帖子
         */
        int result = jsonObject.getInt("result");
        String basis = jsonObject.getStr("basis");

        List<Integer> u_ids = new ArrayList<>();
        reportService.getAllTypeReports(FORUMTYPE.POST).forEach(
                report -> u_ids.add(report.getUId()));

        boolean accept = true;
        switch (result) {
            case 0 -> accept = false;
            case 1 -> {
                // 往texamine表中加入该任务
                if (texamineService.newTaskExamine(o_id, basis) != 1) {
                    throw new RuntimeException("插入失败，请检查服务器");
                }
                // 修改该帖子的可见性
                postService.setPostVisById(o_id, 0);
            }
            case 2 -> // 删除该帖子
                    postService.deletePostById(o_id);
        }

        // 给帖子作者发送帖子待整改消息
        if (result != 0) {
            MessageUtil.newMessage(new UserPostReportMessage(o_id, result, basis,
                    postService.getPostById(o_id).getUserId()));
        }

        // 给所有举报者发送举报结果消息
        for (Integer u_id : u_ids) {
            MessageUtil.newMessage(new PostReportResultMessage(o_id, u_id, accept));
        }

        if (reportService.finishReport(o_id, FORUMTYPE.POST) != 1) {
            throw new RuntimeException("服务器错误");
        }

        return CommonResult.success(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/examine/report/get_replies")
    public CommonResult getReportFloorsAndComment() {
        Map<Integer, FrontendReply> floors = new HashMap<>();
        List<Report> floorReports = reportService.getAllTypeReports(FORUMTYPE.FLOOR);
        for (Report report : floorReports) {
            if (floors.containsKey(report.getOId())) {
                floors.get(report.getOId()).addReason(report.getReason());
            } else {
                Floor floor = floorService.getFloorById(report.getOId());
                FrontendReply frontendReply = new FrontendReply(floor);
                frontendReply.addReason(report.getReason());
                floors.put(report.getOId(), frontendReply);
            }
        }

        Map<Integer, FrontendReply> comments = new HashMap<>();
        List<Report> commentReports = reportService.getAllTypeReports(FORUMTYPE.COMMENT);
        for (Report report : commentReports) {
            if (comments.containsKey(report.getOId())) {
                comments.get(report.getOId()).addReason(report.getReason());
            } else {
                Comment comment = commentService.getCommentById(report.getOId());
                FrontendReply frontendReply = new FrontendReply(comment, floorService.getFloorById(comment.getFloorId()));
                frontendReply.addReason(report.getReason());
                comments.put(report.getOId(), frontendReply);
            }
        }

        List<FrontendReply> replies = new ArrayList<>(floors.values());
        replies.addAll(comments.values());

        return CommonResult.success(replies);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/examine/report/result_of_report_reply/{type}/{o_id}")
    public CommonResult finishReportFloorOrComment(@PathVariable Integer type,
                                                   @PathVariable Integer o_id,
                                                   @RequestBody JSONObject jsonObject) {
        boolean result = jsonObject.getBool("result");
        String basis = jsonObject.getStr("basis");

        if (result) {
            // 举报成功

            // 给原用户发送被举报成功消息
            // 删除楼层或评论
            if (type == 0) {
                floorService.deleteFloorById(o_id);
                MessageUtil.newMessage(new UserFloorReportMessage(o_id, basis,
                        floorService.getFloorById(o_id).getUserId()));
            } else if (type == 1) {
                commentService.deleteCommentById(o_id);
                MessageUtil.newMessage(new UserCommentReportMessage(o_id, basis,
                        commentService.getCommentById(o_id).getCuserId()));
            }
        }

        // 给发起举报的用户发送举报结果消息
        List<Report> reports;
        if (type == 0) {
            reports = reportService.getAllTypeReports(FORUMTYPE.FLOOR);
            for (Report report : reports) {
                MessageUtil.newMessage(new FloorReportResultMessage(report.getOId(), report.getUId(), result));
            }
        } else if (type == 1) {
            reports = reportService.getAllTypeReports(FORUMTYPE.COMMENT);
            for (Report report : reports) {
                MessageUtil.newMessage(new CommentReportResultMessage(report.getOId(), report.getUId(), result));
            }
        }


        if (reportService.finishReport(o_id, type == 0 ? FORUMTYPE.FLOOR : FORUMTYPE.COMMENT) != 1) {
            throw new RuntimeException("服务器错误");
        }

        return CommonResult.success(null);
    }

    @PostMapping("/forum/report/reportPost")
    public CommonResult newPostReport(@RequestParam(value = "id") int id,
                                      @RequestBody JSONObject jsonObject) {
        int post_id = jsonObject.getInt("id");
        String reason = jsonObject.getStr("reason");

        reportService.newReport(reason, post_id, FORUMTYPE.POST, id);

        MessageUtil.newMessage(new PostReportMessage(reason, post_id, id));

        return CommonResult.success(null);
    }

    @PostMapping("/forum/report/reportReply")
    public CommonResult newFloorOrCommentReport(@RequestParam(value = "id") int id,
                                                @RequestBody JSONObject jsonObject) {
        int o_id = jsonObject.getInt("id");
        String reason = jsonObject.getStr("reason");
        int type = jsonObject.getInt("type");

        reportService.newReport(reason, o_id, type == 0 ? FORUMTYPE.FLOOR : FORUMTYPE.COMMENT, id);

        if (type == 0) {
            MessageUtil.newMessage(new FloorReportMessage(reason, o_id, id));
        } else {
            MessageUtil.newMessage(new CommentReportMessage(reason, o_id, id));
        }

        return CommonResult.success(null);
    }
}
