package com.example.backend.controller;

import com.example.backend.domain.BuaaService;
import com.example.backend.domain.Pin;
import com.example.backend.entity.IdWrap;
import com.example.backend.entity.PinGroup;
import com.example.backend.entity.SearchInfo;
import com.example.backend.entity.Text;
import com.example.backend.result.CommonResult;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public CommonResult insertPin(@RequestBody Pin pin) {
        int ret = pinService.insertPin(pin);
        if (ret == 0)
            return CommonResult.failed("pin数据插入失败");
        else
            return CommonResult.success(null);
    }

    @RequestMapping("/pin_search")
    public CommonResult searchPin(@RequestBody Text text) {
        ArrayList<Pin> pins = pinService.searchPin(text.getSearchContext());
        if (pins == null || pins.size() == 0)
            return CommonResult.failed("搜索不到包含字段 '" + text.getSearchContext() + "' 的数据");
        SearchInfo searchInfo = new SearchInfo();
        searchInfo.setSearch_result_list(pins);
        searchInfo.setMax_suit_p_id(pins.get(0).getId());
        return CommonResult.success(searchInfo);
    }

    @RequestMapping("/pin/getPinInfoById")
    public CommonResult getPinInfoById(@RequestBody IdWrap idWrap) {
        int id = idWrap.getId();
        PinGroup pinGroup = new PinGroup();
        Pin pin = pinService.getPinById(id);
        if (pin == null)
            return CommonResult.failed("数据库中不存在id = " + id + "的pin");
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
        return CommonResult.success(pinGroup);
    }

    @RequestMapping("/pin/changePinInfoById")
    public CommonResult changePinInfoById(@RequestBody Pin pin) {
        int ret = pinService.updatePin(pin);
        if (ret == 1)
            return CommonResult.success(null);
        else
            return CommonResult.failed("数据库中不存在id = " + pin.getId() + "的pin");
    }

//    @RequestMapping("/pin/getPinPermission")
//    public CommonResult getPinPermission(@RequestBody IdWrap idWrap) {
//        int id = idWrap.getId();
//        Integer user_type = userService.getUserTypeById(id);
//        if (user_type == null)
//            return CommonResult.failed("数据库中不存在id = " + id + "的user");
//        return CommonResult.success(user_type);
//    }
}
