package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 楼层相关帖子id
     */
    private Integer postId;

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

    public Integer getUserId() {
        return userId;
    }

    public Integer getPostId() {
        return postId;
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

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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
