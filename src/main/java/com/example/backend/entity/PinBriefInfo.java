package com.example.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinBriefInfo {
    private Integer id;
    private String name;
    private Integer type;
    private Integer visibility;
    private Double[] lnglat;
}
