package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
@Data
public class Message implements Serializable {
    /**
     * 消息id，系统自动生成
     */
    @TableId(value = "m_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 消息内容
     */
    @TableField(value = "m_content")
    private String content;

    /**
     * 消息状态，0为未读，1为已读
     */
    @TableField(value = "m_status")
    private Integer status;

    /**
     * 消息携带的参数
     */
    @TableField(value = "m_para")
    private String para;

    /**
     * 消息类型
     */
    @TableField(value = "m_type")
    private Integer type;

    /**
     * 消息接收用户
     */
    @TableField(value = "ureceive_id")
    private Integer ureceiveId;

    /**
     * 消息生成时间
     */
    @TableField(value = "m_time")
    private Date time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}