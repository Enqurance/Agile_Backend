package com.example.backend.entity.message;

import com.example.backend.domain.Message;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @className: TitleGenerator
 * @Description: 根据Message的type生成不同title
 * @author: WAN
 * @date: 2023/5/9 16:16
 */
@Component
public class TitleGenerator {
    private static UserService userService;

    private static PinService pinService;

    private static PostService postService;

    private static FloorService floorService;

    private static CommentService commentService;

    public static String generateTitle(Message message) {
        String title;
        switch (message.getType()) {
            case 1 -> title = likeTitle(message);
            case 2 -> title = replyTitle(message);
            case 3 -> title = pinApplySuccessTitle(message);
            case 4 -> title = postReleaseSuccessTitle(message);
            case 5 -> title = pinTaskTitle(message);
            case 6 -> title = postReportTitle(message);
            case 7 -> title = floorReportTitle(message);
            case 8 -> title = commentReportTitle(message);
            case 9 -> title = examinePostTitle(message);
            case 10 -> title = pinApplyResultTitle(message);
            case 11 -> title = postReportResultTitle(message);
            case 12 -> title = floorReportResultTitle(message);
            case 13 -> title = commentReportResultTitle(message);
            case 14 -> title = pinFeedbackSuccessTitle(message);
            case 15 -> title = pinFeedbackResultTitle(message);
            case 16 -> title = userPostReportTitle(message);
            case 17 -> title = userFloorReportTitle();
            case 18 -> title = userCommentReportTitle();
            case 19 -> title = rectifyResultTitle(message);
            case 20 -> title = violationMessageTitle(message);
            default -> title = "错误的消息种类";
        }
        return title;
    }

    @Autowired
    public void setUserService(UserService userService) {
        TitleGenerator.userService = userService;
    }

    @Autowired
    public void setPinService(PinService pinService) {
        TitleGenerator.pinService = pinService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        TitleGenerator.postService = postService;
    }

    @Autowired
    public void setFloorService(FloorService floorService) {
        TitleGenerator.floorService = floorService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        TitleGenerator.commentService = commentService;
    }

    private static String surroundInfo(String info) {
        return "<" + info + ">";
    }

    private static String likeTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    surroundInfo(userService.findUserById(paras[1]).get(0).getName()) +
                    "点赞了你的帖子" +
                    surroundInfo(postService.getPostById(paras[0]).getTitle());
        } catch (Exception e) {
            return "您的帖子已删除";
        }
    }

    private static String replyTitle(Message message) {
        try {
            return MESSTYPE.NOTICE.getType() +
                    surroundInfo(userService.findUserById(Integer.parseInt(
                            message.getPara().split(";")[1]
                    )).get(0).getName()) +
                    "回复了你";
        } catch (Exception e) {
            return "该用户不存在";
        }

    }

    private static String pinApplySuccessTitle(Message message) {
        try {
            return MESSTYPE.NOTICE.getType() +
                    "地图钉" +
                    surroundInfo(pinService.getPinById(Integer.parseInt(
                            message.getPara()
                    )).getName()) +
                    "成功发起公开申请，请等待审核";
        } catch (Exception e) {
            return "您公开申请的地图钉被删除";
        }

    }

    private static String postReleaseSuccessTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    "您的帖子" +
                    surroundInfo(postService.getPostById(paras[0]).getTitle()) +
                    "发布成功";
        } catch (Exception e) {
            return "您的帖子已删除";
        }

    }

    private static String pinTaskTitle(Message message) {
        try {
            return MESSTYPE.APPLY.getType() +
                    "地图钉" +
                    surroundInfo(pinService.getPinById(Integer.parseInt(
                            message.getPara()
                    )).getName()) +
                    "申请公开";
        } catch (Exception e) {
            return "地图钉不存在";
        }

    }

    private static String postReportTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.REPORT.getType() +
                    "帖子" +
                    surroundInfo(postService.getPostById(paras[0]).getTitle()) +
                    "被举报";
        } catch (Exception e) {
            return "帖子不存在";
        }

    }

    private static String floorReportTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.REPORT.getType() +
                    "楼层" +
                    surroundInfo(floorService.getFloorById(paras[0]).getContent()) +
                    "被举报";
        } catch (Exception e) {
            return "楼层不存在";
        }

    }

    private static String commentReportTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.REPORT.getType() +
                    "评论" +
                    surroundInfo(commentService.getCommentById(paras[0]).getContent()) +
                    "被举报";
        } catch (Exception e) {
            return "评论不存在";
        }

    }

    private static String examinePostTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.APPLY.getType() +
                    "帖子" +
                    surroundInfo(postService.getPostById(paras[0]).getTitle()) +
                    "申请发布";
        } catch (Exception e) {
            return "帖子不存在";
        }

    }

    private static String pinApplyResultTitle(Message message) {
        try {
            return MESSTYPE.NOTICE.getType() +
                    "您的地图钉" +
                    surroundInfo(pinService.getPinById(Integer.parseInt(
                            message.getPara()
                    )).getName()) +
                    "申请" +
                    message.getPara().split(";")[1];
        } catch (Exception e) {
            return "地图钉已删除";
        }

    }

    private static String postReportResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    "您对帖子" +
                    surroundInfo(postService.getPostById(paras[0]).getTitle()) +
                    "的举报" +
                    (paras[1] == 1 ? "成功" : "失败");
        } catch (Exception e) {
            return "您举报的帖子已删除";
        }

    }

    private static String floorReportResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    "您对楼层" +
                    surroundInfo(floorService.getFloorById(paras[0]).getContent()) +
                    "的举报" +
                    (paras[1] == 1 ? "成功" : "失败");
        } catch (Exception e) {
            return "您举报的楼层已删除";
        }

    }

    private static String commentReportResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    "您对评论" +
                    surroundInfo(commentService.getCommentById(paras[0]).getContent()) +
                    "的举报" +
                    (paras[1] == 1 ? "成功" : "失败");
        } catch (Exception e) {
            return "您举报的评论已删除";
        }

    }

    private static String pinFeedbackSuccessTitle(Message message) {
        try {
            return MESSTYPE.FEEDBACK.getType() +
                    "地图钉" +
                    surroundInfo(pinService.getPinById(Integer.parseInt(message.getPara())).getName()) +
                    "反馈成功，请等待审核";
        } catch (Exception e) {
            return "您反馈的地图钉已删除";
        }

    }

    private static String pinFeedbackResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    "您对地图钉" +
                    surroundInfo(pinService.getPinById(paras[0]).getName()) +
                    "反馈已完成审核";
        } catch (Exception e) {
            return "您反馈的地图钉已删除";
        }

    }

    private static String userPostReportTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            return MESSTYPE.NOTICE.getType() +
                    "您的帖子" +
                    surroundInfo(postService.getPostById(paras[0]).getTitle()) +
                    "被成功举报，" +
                    (paras[1] == 1 ? "请完成修改后提交申请" : "已被删除");
        } catch (Exception e) {
            return "您的帖子已删除";
        }

    }

    private static String userFloorReportTitle() {
        return MESSTYPE.NOTICE.getType() +
                "您的楼层被成功举报，已被删除";
    }

    private static String userCommentReportTitle() {
        return MESSTYPE.NOTICE.getType() +
                "您的评论被成功举报，已被删除";
    }

    private static String rectifyResultTitle(Message message) {
        Integer[] paras = Arrays.stream(message.getPara().split(";"))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);

        try {
            StringBuilder sb = new StringBuilder(
                    MESSTYPE.NOTICE.getType() +
                            "您的帖子" +
                            surroundInfo(postService.getPostById(paras[0]).getTitle()) +
                            "整改");

            if (paras[1] == 0) {
                sb.append("通过");
            } else {
                sb.append("未通过，");
                if (paras[1] == 1) {
                    sb.append("请完成修改后提交申请");
                } else {
                    sb.append("已被删除");
                }
            }

            return sb.toString();
        } catch (Exception e) {
            return "您整改的帖子已删除";
        }
    }

    private static String violationMessageTitle(Message message) {
        return MESSTYPE.WARING.getType() +
                "您发布的" +
                message.getPara() +
                "疑似存在违规内容，已删除";
    }
}
