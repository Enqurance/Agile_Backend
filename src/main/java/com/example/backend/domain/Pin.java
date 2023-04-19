package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @TableName pin
 */
@TableName(value ="pin")
@Data
public class Pin implements Serializable {
    /**
     * 地图钉id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 地图钉名
     */
    private String name;

    /**
     * 地图钉坐标
     */
    private String lnglat;

    /**
     * 地图钉位置
     */
    private String position;

    /**
     * 地图钉相关介绍
     */
    private String brief;

    /**
     * 地图钉Tag
     */
    private Integer type;

    /**
     * 地点开放时间
     */
    private String openTime;

    /**
     * 地点联系电话
     */
    private String phone;

    /**
     * 地点联系电话
     */
    private Integer visibility;

    /**
     * 用户id，个人用户创建pin为私有的，管理员创建pin为公开的
     */
    private Integer user_id;

    /**
     * 地图钉相关论坛id
     */
    private Integer forum_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Pin() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLnglat() {
        return lnglat;
    }

    public String getPosition() {
        return position;
    }

    public String getBrief() {
        return brief;
    }

    public Integer getType() {
        return type;
    }

    public String getOpenTime() {
        return openTime;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getForum_id() {
        return forum_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLnglat(String lnglat) {
        this.lnglat = lnglat;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setForum_id(Integer forum_id) {
        this.forum_id = forum_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("[");
        sb.append("id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", position=").append(position);
        sb.append(", brief=").append(brief);
        sb.append(", type=").append(type);
        sb.append(", openTime=").append(openTime);
        sb.append(", phone=").append(phone);
        sb.append(", user_id=").append(user_id);
        sb.append(", forum_id=").append(forum_id);
        sb.append("]");
        return sb.toString();
    }
}