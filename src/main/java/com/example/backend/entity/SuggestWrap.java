package com.example.backend.entity;

import lombok.Data;

@Data
public class SuggestWrap {
    private String suggestion;

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
}
