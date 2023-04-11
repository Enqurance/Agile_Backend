package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

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
    private String type;

    /**
     * 地点开放时间
     */
    private String openTime;

    /**
     * 地点联系电话
     */
    private String phone;

    /**
     * 用户id，个人用户创建pin为私有的，管理员创建pin为公开的
     */
    private Integer user_id;

    /**
     * 地图钉相关照片组id
     */
    private Integer photo_id;

    /**
     * 地图钉相关论坛id
     */
    private Integer forum_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Pin() {}

    public Pin(Integer id, String name, String position, String brief,
               String type, String openTime, String phone, Integer user_id,
               Integer photo_id, Integer forum_id) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.brief = brief;
        this.type = type;
        this.openTime = openTime;
        this.phone = phone;
        this.user_id = user_id;
        this.photo_id = photo_id;
        this.forum_id = forum_id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getBrief() {
        return brief;
    }

    public String getType() {
        return type;
    }

    public String getpOpentime() {
        return openTime;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public Integer getPhoto_id() {
        return photo_id;
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

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setpOpentime(String pOpentime) {
        this.openTime = pOpentime;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }

    public void setForum_id(Integer forum_id) {
        this.forum_id = forum_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(id);
        sb.append(", pName=").append(name);
        sb.append(", pPos=").append(position);
        sb.append(", pBrief=").append(brief);
        sb.append(", pTag=").append(type);
        sb.append(", pOpentime=").append(openTime);
        sb.append(", pPhone=").append(phone);
        sb.append(", uId=").append(user_id);
        sb.append(", phId=").append(photo_id);
        sb.append(", fId=").append(forum_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}