package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName report
 */
@TableName(value ="report")
@Data
public class Report implements Serializable {
    /**
     * 举报id，系统自动生成
     */
    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 举报内容id
     */
    @TableField(value = "o_id")
    private Integer oId;

    /**
     * 举报原因
     */
    @TableField(value = "r_reason")
    private String reason;

    /**
     * 举报人id
     */
    @TableField(value = "u_id")
    private Integer uId;

    /**
     * 举报内容类型
     */
    @TableField(value = "r_type")
    private Integer type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}