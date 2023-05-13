package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: FrontendTpin
 * @Description: 前端获取地图钉公开任务的数据模型
 * @author: WAN
 * @date: 2023/5/13 11:10
 */
@Data
@AllArgsConstructor
public class FrontendTpin {
    private Integer id;
    private String name;
    private Integer type;
    private Integer visibility;
    private Double[] lnglat;
}
