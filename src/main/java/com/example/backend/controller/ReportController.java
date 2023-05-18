package com.example.backend.controller;

import com.example.backend.domain.Comment;
import com.example.backend.domain.Floor;
import com.example.backend.domain.Post;
import com.example.backend.domain.Report;
import com.example.backend.entity.FORUMTYPE;
import com.example.backend.entity.FrontendReply;
import com.example.backend.entity.FrontendReportPost;
import com.example.backend.result.CommonResult;
import com.example.backend.service.CommentService;
import com.example.backend.service.FloorService;
import com.example.backend.service.PostService;
import com.example.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return CommonResult.success(posts.keySet());
    }

    @PostMapping("/examine/report/result_of_report_post/{o_id}")
    public CommonResult finishReportPost(@PathVariable Integer o_id) {
        if (reportService.finishReport(o_id, FORUMTYPE.POST) != 1) {
            throw new RuntimeException("服务器错误");
        }

        return CommonResult.success(null);
    }

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
        List<Report> commentReports = reportService.getAllTypeReports(FORUMTYPE.FLOOR);
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

        return CommonResult.success(floors.keySet().addAll(comments.keySet()));
    }

    @PostMapping("/examine/report/result_of_report_reply/{type}/{o_id}")
    public CommonResult finishReportFloorOrComment(@PathVariable Integer type, @PathVariable Integer o_id) {
        if (reportService.finishReport(o_id, type == 0 ? FORUMTYPE.FLOOR : FORUMTYPE.COMMENT) != 1) {
            throw new RuntimeException("服务器错误");
        }

        return CommonResult.success(null);
    }
}
