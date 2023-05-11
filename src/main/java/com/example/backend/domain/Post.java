package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName post
 */
@TableName(value ="post")
@Data
public class Post implements Serializable {
    /**
     * 帖子id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer postId;

    /**
     * 帖子标题名
     */
    private String postTitle;

    /**
     * 帖子内容
     */
    private String postContent;

    /**
     * 帖子可见性
     */
    private Integer postVisibility;

    /**
     * 帖子对应tag
     */
    private Integer postTag;

    /**
     * 帖子点赞数
     */
    private Integer postThumbsUp;

    /**
     * 帖子访问量
     */
    private Integer postVisit;

    /**
     * 帖子总楼数
     */
    private Integer postFloorNum;

    /**
     * 相关地图钉id组成的字符串，id之间用;隔开
     */
    private String pinIdStr;

    /**
     * 用户id
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Post() {}

    public Integer getPostId() {
        return postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public Integer getPostVisibility() {
        return postVisibility;
    }

    public Integer getPostTag() {
        return postTag;
    }

    public Integer getPostThumbsUp() {
        return postThumbsUp;
    }

    public Integer getPostVisit() {
        return postVisit;
    }

    public Integer getPostFloorNum() {
        return postFloorNum;
    }

    public String getPinIdStr() {
        return pinIdStr;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public void setPostVisibility(Integer postVisibility) {
        this.postVisibility = postVisibility;
    }

    public void setPostTag(Integer postTag) {
        this.postTag = postTag;
    }

    public void setPostThumbsUp(Integer postThumbsUp) {
        this.postThumbsUp = postThumbsUp;
    }

    public void setPostVisit(Integer postVisit) {
        this.postVisit = postVisit;
    }

    public void setPostFloorNum(Integer postFloorNum) {
        this.postFloorNum = postFloorNum;
    }

    public void setPinIdStr(String pinIdStr) {
        this.pinIdStr = pinIdStr;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", postContent=").append(postContent);
        sb.append(", postVisibility=").append(postVisibility);
        sb.append(", postTag=").append(postTag);
        sb.append(", postThumbsUp=").append(postThumbsUp);
        sb.append(", postVisit=").append(postVisit);
        sb.append(", postFloorNum=").append(postFloorNum);
        sb.append(", pinIdStr=").append(pinIdStr);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
