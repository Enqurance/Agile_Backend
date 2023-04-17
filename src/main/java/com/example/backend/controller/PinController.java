package com.example.backend.controller;

import com.example.backend.domain.BuaaService;
import com.example.backend.domain.Pin;
import com.example.backend.domain.User;
import com.example.backend.entity.*;
import com.example.backend.result.CommonResult;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    @RequestMapping("/pin/addPinByCoords")
    public CommonResult addPinByCoords(@RequestBody Pin pin, @RequestParam(name = "id") Integer id) {
        List<User> users = userService.findUserById(id);
        if (users.size() == 0)
            return CommonResult.failed("不存在id = " + id + "的user");
        if (users.get(0).getType() == 0)
            pin.setVisibility(0);
        else if (users.get(0).getType() == 1)
            pin.setVisibility(1);
        pin.setUser_id(id);
        pin.setForum_id(1);
        pin.setPhoto_id(1);
        int ret = pinService.insertPin(pin);
        if (ret == 0)
            return CommonResult.failed("pin数据插入失败");
        else
            return CommonResult.success(pinService.findMaxId());
    }

    @DeleteMapping("/pin/deletePin/{pin_id}")
    public CommonResult deletePinById(@PathVariable(value = "pin_id", required = false) Integer pin_id) {
        int ret = pinService.deletePinById(pin_id);
        if (ret == 0)
            return CommonResult.failed("删除id = " + pin_id + "的pin失败");
        else
            return CommonResult.success(null);
    }

    @RequestMapping("/pin_search")
    public CommonResult searchPin(@RequestBody Text text, @RequestParam(name = "id") Integer id) {
        ArrayList<Pin> pins = pinService.searchPin(text.getSearchContext(), id);
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

    @GetMapping("/getUserAllBriefPin")
    public CommonResult getUserAllBriefPin(@RequestParam(name = "id") Integer id) {
        List<PinBriefInfo> briefInfos = new ArrayList<>();
        for (Pin pin : pinService.getUserAllBriefPin(id)) {
            briefInfos.add(new PinBriefInfo(
                    pin.getId(),
                    pin.getName(),
                    pin.getType(),
                    pin.getVisibility(),
                    Arrays.stream(pin.getLnglat().split(";"))
                            .map(Double::parseDouble)
                            .toArray(Double[]::new)
            ));
        }
        return CommonResult.success(briefInfos);
    }
}
