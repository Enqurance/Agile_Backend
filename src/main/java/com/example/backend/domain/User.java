package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 用户id，系统自动生成
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField(value = "u_name")
    private String name;

    /**
     * 用户邮箱，不可重复
     */
    @TableField(value = "u_email")
    private String email;

    /**
     * 用户类型，TRUE为管理员
     */
    @TableField(value = "u_type")
    private Integer type;

    /**
     * 用户密码，加密
     */
    @TableField(value = "u_password")
    private String password;

    /**
     * 头像路径
     */
    @TableField(value = "u_icon")
    private String icon;

    /**
     * 用户校区
     */
    @TableField(value = "u_campus")
    private String campus;

    /**
     * 用户年级
     */
    @TableField(value = "u_grade")
    private String grade;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}