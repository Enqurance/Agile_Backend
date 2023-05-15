package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

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
    private Integer id;

    /**
     * 帖子标题名
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 帖子可见性
     */
    private Integer visibility;

    /**
     * 帖子对应tag
     */
    private Integer tag;

    /**
     * 帖子点赞数
     */
    private Integer thumbsUp;

    /**
     * 帖子访问量
     */
    private Integer visit;

    /**
     * 帖子总楼数
     */
    private Integer floorNum;

    /**
     * 相关地图钉id组成的字符串，id之间用;隔开
     */
    private String pinIdStr;

    private Date createTime;

    /**
     * 用户id
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Post() {}

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public Integer getTag() {
        return tag;
    }

    public Integer getThumbsUp() {
        return thumbsUp;
    }

    public Integer getVisit() {
        return visit;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public String getPinIdStr() {
        return pinIdStr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public void setThumbsUp(Integer thumbsUp) {
        this.thumbsUp = thumbsUp;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public void setPinIdStr(String pinIdStr) {
        this.pinIdStr = pinIdStr;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", postId=").append(id);
        sb.append(", postTitle=").append(title);
        sb.append(", postContent=").append(content);
        sb.append(", postVisibility=").append(visibility);
        sb.append(", postTag=").append(tag);
        sb.append(", postThumbsUp=").append(thumbsUp);
        sb.append(", postVisit=").append(visit);
        sb.append(", postFloorNum=").append(floorNum);
        sb.append(", pinIdStr=").append(pinIdStr);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
