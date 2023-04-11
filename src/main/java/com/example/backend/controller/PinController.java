package com.example.backend.controller;

import com.example.backend.domain.BuaaService;
import com.example.backend.domain.Pin;
import com.example.backend.entity.PinGroup;
import com.example.backend.entity.Text;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/map")
public class PinController {
    @Autowired
    PinService pinService;
    @Autowired
    PhotoService photoService;
    @Autowired
    PinServiceRelService pinServiceRelService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    UserService userService;

    @RequestMapping("/insert")
    public int insertPin(@RequestBody Pin pin) {
        int ret = pinService.insertPin(pin);
        return ret;
    }

    @RequestMapping("/pin_search")
    public List<Pin> searchPin(@RequestBody Text text) {
        List<Pin> pins = pinService.searchPin(text.getSearchContext());
        return pins;
    }

    @RequestMapping("/pin/getPinInfoById")
    public PinGroup getPinInfoById(@RequestBody Integer id) {
        PinGroup pinGroup = new PinGroup();
        Pin pin = pinService.getPinById(id);
        ArrayList<String> photos = photoService.getPhotoUrlById(pin.getPhoto_id());
        ArrayList<Integer> serviceId = pinServiceRelService.getServiceIdByPinId(id);
        ArrayList<BuaaService> services = new ArrayList<>();
        for (Integer sId : serviceId) {
            BuaaService buaaService = serviceService.getServiceById(sId);
            buaaService.setPhoto(photoService.getUrlStrById(buaaService.getPhoto_id()));
            services.add(buaaService);
        }
        pinGroup.setPin_id(id);
        pinGroup.setPin(pin);
        pinGroup.setPhotos(photos);
        pinGroup.setServices(services);
        return pinGroup;
    }

    @RequestMapping("/pin/changePinInfoById")
    public int changePinInfoById(@RequestBody Pin pin) {
        int ret = pinService.updatePin(pin);
        return ret;
    }

    @RequestMapping("/pin/getPinPermission")
    public int getPinPermission(@RequestBody Integer id) {
        int user_type = userService.getUserTypeById(id);
        return user_type;
    }
}
