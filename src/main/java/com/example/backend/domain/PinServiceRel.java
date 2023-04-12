package com.example.backend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName pin_service
 */
@TableName(value ="pin_service")
@Data
public class PinServiceRel implements Serializable {
    /**
     * 标识id，系统自动生成
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 相关地图钉id
     */
    private Integer pin_id;

    /**
     * 服务id
     */
    private Integer service_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public Integer getPin_id() {
        return pin_id;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPin_id(Integer pin_id) {
        this.pin_id = pin_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pSId=").append(id);
        sb.append(", pId=").append(pin_id);
        sb.append(", sId=").append(service_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}