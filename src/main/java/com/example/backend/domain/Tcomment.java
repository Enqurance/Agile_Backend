package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tcomment
 */
@TableName(value ="tcomment")
@Data
public class Tcomment implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "tcomment_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 评论id
     */
    @TableField(value = "comment_id")
    private Integer commentId;

    /**
     * 举报原因
     */
    @TableField(value = "tcomment_reason")
    private String reason;

    /**
     * 举报人id
     */
    @TableField(value = "u_id")
    private String uId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}