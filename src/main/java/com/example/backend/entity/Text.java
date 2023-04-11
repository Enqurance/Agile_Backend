package com.example.backend.entity;

import lombok.Data;

@Data
public class Text {
    private String searchContext;

    public Text() {}

    public Text(String searchContext) {
        this.searchContext = searchContext;
    }

    public String getSearchContext() {
        return searchContext;
    }

    public void setSearchContext(String searchContext) {
        this.searchContext = searchContext;
    }
}
