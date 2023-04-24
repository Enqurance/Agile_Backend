package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @TableName suggestion
 */
@TableName(value ="suggestion")
@Data
public class Suggestion implements Serializable {
    /**
     * 用户id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户相关建议
     */
    private String str;

    /**
     * 用户id，外键
     */
    private Integer user_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public String getStr() {
        return str;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sugId=").append(id);
        sb.append(", sugStr=").append(str);
        sb.append(", uId=").append(user_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
