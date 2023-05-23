package com.example.backend.entity;

import com.example.backend.domain.Floor;
import lombok.Data;

import java.util.List;

@Data
public class ListFloors {
    private List<Floor> retFloors;
    private Integer length;

    public ListFloors(List<Floor> retFloors, Integer length) {
        this.retFloors = retFloors;
        this.length = length;
    }
}
