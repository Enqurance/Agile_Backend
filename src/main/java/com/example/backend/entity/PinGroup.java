package com.example.backend.entity;

import com.example.backend.domain.BuaaService;
import com.example.backend.domain.Pin;

import java.util.ArrayList;

public class PinGroup {
    private Integer pin_id;
    private Pin pin;
    private ArrayList<String> photos;
    private ArrayList<BuaaService> services;

    public PinGroup() {}

    public PinGroup(Integer pin_id, Pin pin, ArrayList<String> photos, ArrayList<BuaaService> services) {
        this.pin_id = pin_id;
        this.pin = pin;
        this.photos = photos;
        this.services = services;
    }

    public Integer getPin_id() {
        return pin_id;
    }

    public Pin getPin() {
        return pin;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public ArrayList<BuaaService> getServices() {
        return services;
    }

    public void setPin_id(Integer pin_id) {
        this.pin_id = pin_id;
    }

    public void setPin(Pin pin) {
        this.pin = pin;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    public void setServices(ArrayList<BuaaService> services) {
        this.services = services;
    }
}
