package com.example.backend.entity;

import com.example.backend.domain.Pin;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PublicPins {
    private List<Pin> tag1 = new ArrayList<>();
    private List<Pin> tag2 = new ArrayList<>();
    private List<Pin> tag3 = new ArrayList<>();
    private List<Pin> tag4 = new ArrayList<>();
    private List<Pin> tag5 = new ArrayList<>();
    private List<Pin> tag6 = new ArrayList<>();
    private List<Pin> tag7 = new ArrayList<>();

    public PublicPins() {}

    public void setTag1(List<Pin> tag1) {
        this.tag1 = tag1;
    }

    public void setTag2(List<Pin> tag2) {
        this.tag2 = tag2;
    }

    public void setTag3(List<Pin> tag3) {
        this.tag3 = tag3;
    }

    public void setTag4(List<Pin> tag4) {
        this.tag4 = tag4;
    }

    public void setTag5(List<Pin> tag5) {
        this.tag5 = tag5;
    }

    public void setTag6(List<Pin> tag6) {
        this.tag6 = tag6;
    }

    public void setTag7(List<Pin> tag7) {
        this.tag7 = tag7;
    }

    public List<Pin> getTag1() {
        return tag1;
    }

    public List<Pin> getTag2() {
        return tag2;
    }

    public List<Pin> getTag3() {
        return tag3;
    }

    public List<Pin> getTag4() {
        return tag4;
    }

    public List<Pin> getTag5() {
        return tag5;
    }

    public List<Pin> getTag6() {
        return tag6;
    }

    public List<Pin> getTag7() {
        return tag7;
    }
}
