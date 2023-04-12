package com.example.backend.entity;

import com.example.backend.domain.Pin;
import lombok.Data;

import java.util.ArrayList;

@Data
public class SearchInfo {
    private Integer max_suit_p_id;
    private ArrayList<Pin> search_result_list;

    public SearchInfo() {}

    public Integer getMax_suit_p_id() {
        return max_suit_p_id;
    }

    public ArrayList<Pin> getSearch_result_list() {
        return search_result_list;
    }

    public void setMax_suit_p_id(Integer max_suit_p_id) {
        this.max_suit_p_id = max_suit_p_id;
    }

    public void setSearch_result_list(ArrayList<Pin> search_result_list) {
        this.search_result_list = search_result_list;
    }
}
