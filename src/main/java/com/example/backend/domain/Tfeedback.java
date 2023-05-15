package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tfeedback
 */
@TableName(value ="tfeedback")
@Data
public class Tfeedback implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "tfeedback_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地图钉id
     */
    @TableField(value = "p_id")
    private Integer pId;

    /**
     * 反馈标题
     */
    @TableField(value = "tfeedback_title")
    private String title;

    /**
     * 反馈理由
     */
    @TableField(value = "tfeedback_content")
    private String content;

    /**
     * 反馈用户id
     */
    @TableField(value = "u_id")
    private Integer uId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}