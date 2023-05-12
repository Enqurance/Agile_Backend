package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tpin
 */
@TableName(value ="tpin")
@Data
public class Tpin implements Serializable {
    /**
     * 任务id，系统自动生成
     */
    @TableId(value = "tpin_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 地图钉id
     */
    @TableField(value = "p_id")
    private Integer pId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}