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
    private Integer pId;

    /**
     * 地图钉名
     */
    private String pName;

    /**
     * 地图钉位置
     */
    private String pPos;

    /**
     * 地图钉相关介绍
     */
    private String pBrief;

    /**
     * 地图钉Tag
     */
    private String pTag;

    /**
     * 地点开放时间
     */
    private String pOpentime;

    /**
     * 地点联系电话
     */
    private String pPhone;

    /**
     * 用户id，个人用户创建pin为私有的，管理员创建pin为公开的
     */
    private Integer uId;

    /**
     * 地图钉相关照片组id
     */
    private Integer phId;

    /**
     * 地图钉相关论坛id
     */
    private Integer fId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Pin() {}

    public Pin(Integer pId, String pName, String pPos, String pBrief, String pTag,
               String pOpentime, String pPhone, Integer uId, Integer phId, Integer fId) {
        this.pId = pId;
        this.pName = pName;
        this.pPos = pPos;
        this.pBrief = pBrief;
        this.pTag = pTag;
        this.pOpentime = pOpentime;
        this.pPhone = pPhone;
        this.uId = uId;
        this.phId = phId;
        this.fId = fId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(pId);
        sb.append(", pName=").append(pName);
        sb.append(", pPos=").append(pPos);
        sb.append(", pBrief=").append(pBrief);
        sb.append(", pTag=").append(pTag);
        sb.append(", pOpentime=").append(pOpentime);
        sb.append(", pPhone=").append(pPhone);
        sb.append(", uId=").append(uId);
        sb.append(", phId=").append(phId);
        sb.append(", fId=").append(fId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}