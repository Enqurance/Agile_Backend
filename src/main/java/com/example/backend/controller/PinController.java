package com.example.backend.controller;

import com.example.backend.domain.Pin;
import com.example.backend.entity.Text;
import com.example.backend.service.PinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map")
public class PinController {
    @Autowired
    PinService pinService;

    @RequestMapping("/insert")
    @ResponseBody
    public int insertPin(@RequestBody Pin pin) {
        int ret = pinService.insertPin(pin);
        return ret;
    }

    @RequestMapping("/pin_search")
    public List<Pin> searchPin(@RequestBody Text text) {
        List<Pin> pins = pinService.searchPin(text.getSearchContext());
        return pins;
    }
}
