package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName treport
 */
@TableName(value ="treport")
@Data
public class Treport implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "treport_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 举报内容id
     */
    @TableField(value = "o_id")
    private Integer oId;

    /**
     * 举报内容类型
     */
    @TableField(value = "treport_type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}