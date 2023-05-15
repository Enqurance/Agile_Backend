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
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 回复id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 回复内容
     */
    private String content;

    private Date createTime;

    /**
     * 所属楼层id，外键
     */
    private Integer floorId;

    /**
     * 创建用户id，外键
     */
    private Integer cuserId;

    /**
     * 回复用户id，外键，可以为空，代表没有回复
     */
    private Integer ruserId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Comment() {}

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public Integer getCuserId() {
        return cuserId;
    }

    public Integer getRuserId() {
        return ruserId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public void setCuserId(Integer cuserId) {
        this.cuserId = cuserId;
    }

    public void setRuserId(Integer ruserId) {
        this.ruserId = ruserId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", commentId=").append(id);
        sb.append(", cContent=").append(content);
        sb.append(", floorId=").append(floorId);
        sb.append(", cuserId=").append(cuserId);
        sb.append(", ruserId=").append(ruserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
