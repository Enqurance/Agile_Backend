package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
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

    /**
     * 用户性别
     */
    @TableField(value = "u_gender")
    private Integer gender;

    /**
     * 个人简介
     */
    @TableField(value = "u_description")
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getType() {
        return type;
    }

    public String getPassword() {
        return password;
    }

    public String getIcon() {
        return icon;
    }

    public String getCampus() {
        return campus;
    }

    public String getGrade() {
        return grade;
    }

    public Integer getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}