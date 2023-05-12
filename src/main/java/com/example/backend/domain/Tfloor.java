package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tfloor
 */
@TableName(value ="tfloor")
@Data
public class Tfloor implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "tfloor_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 楼层id
     */
    @TableField(value = "floor_id")
    private Integer floorId;

    /**
     * 举报原因
     */
    @TableField(value = "tfloor_reason")
    private String reason;

    /**
     * 举报人id
     */
    @TableField(value = "u_id")
    private String uId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}