package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName service
 */
@TableName(value ="service")
@Data
public class BuaaService implements Serializable {
    /**
     * 服务id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名
     */
    private String name;

    /**
     * 服务简介
     */
    private String brief;

    private String photo;

    /**
     * 服务相关照片组id
     */
    private Integer photo_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrief() {
        return brief;
    }

    public String getPhoto() {
        return photo;
    }

    public Integer getPhoto_id() {
        return photo_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setPhoto_id(Integer photo_id) {
        this.photo_id = photo_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sId=").append(id);
        sb.append(", sName=").append(name);
        sb.append(", sBrief=").append(brief);
        sb.append(", phId=").append(photo_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}