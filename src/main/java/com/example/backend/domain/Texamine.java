package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName texamine
 */
@TableName(value ="texamine")
@Data
public class Texamine implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "texamine_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 原帖子内容
     */
    @TableField(value = "post_id")
    private Integer postId;

    /**
     * 修改后的标题
     */
    @TableField(value = "texamine_title")
    private String title;

    /**
     * 修改后的内容
     */
    @TableField(value = "texamine_content")
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}