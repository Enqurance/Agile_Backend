package com.example.backend.entity;

import lombok.Data;

@Data
public class IdWrap {
    private Integer id;

    public IdWrap() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
