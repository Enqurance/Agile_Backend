package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 *
 * @TableName floor
 */
@TableName(value ="floor")
@Data
public class Floor implements Serializable {
    /**
     * 楼层id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 楼层内容
     */
    private String content;

    /**
     * 帖子的第几楼
     */
    private Integer layers;

    private Date createTime;

    /**
     * 用户id
     */
    private Integer userId;

    private String userName;

    /**
     * 楼层相关帖子id
     */
    private Integer postId;

    private Integer is_auth;

    private Comment comment_cases;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Floor() {}

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getLayers() {
        return layers;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getIs_auth() {
        return is_auth;
    }

    public Comment getComment_cases() {
        return comment_cases;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLayers(Integer layers) {
        this.layers = layers;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setIs_auth(Integer is_auth) {
        this.is_auth = is_auth;
    }

    public void setComment_cases(Comment comment_cases) {
        this.comment_cases = comment_cases;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", floorId=").append(id);
        sb.append(", floorContent=").append(content);
        sb.append(", floorLayers=").append(layers);
        sb.append(", userId=").append(userId);
        sb.append(", postId=").append(postId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
