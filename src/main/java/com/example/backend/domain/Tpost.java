package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tpost
 */
@TableName(value ="tpost")
@Data
public class Tpost implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "tpost_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 帖子id
     */
    @TableField(value = "post_id")
    private Integer postId;

    /**
     * 举报原因
     */
    @TableField(value = "tpost_reason")
    private String reason;

    /**
     * 举报人id
     */
    @TableField(value = "u_id")
    private String uId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}