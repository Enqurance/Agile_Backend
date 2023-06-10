package com.example.backend.controller;

import com.example.backend.domain.BuaaService;
import com.example.backend.domain.Forum;
import com.example.backend.domain.Pin;
import com.example.backend.domain.User;
import com.example.backend.entity.*;
import com.example.backend.result.CommonResult;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    ForumService forumService;

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/pin/addPinByCoords")
    public CommonResult addPinByCoords(@RequestBody Pin pin,
                                       @RequestParam(name = "id") Integer id) {
        User user = userService.findUserById(id).get(0);
        // pin的可见性由后端设定
        pin.setVisibility(user.getType());
        pin.setUser_id(id);
        Forum forum = new Forum();
        forumService.insertForum(forum);
        pin.setForum_id(forumService.findMaxId());
        int ret = pinService.insertPin(pin);
        if (ret == 0)
            return CommonResult.failed("pin数据插入失败");
        else
            return CommonResult.success(pinService.findMaxId());
    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping("/pin/deletePin/{pin_id}")
    public CommonResult deletePinById(@PathVariable(value = "pin_id") Integer pin_id,
                                      @RequestParam(value = "id") Integer id) {
        Pin pin = pinService.getPinById(pin_id);
        if (pin == null)
            return CommonResult.failed("不存在id = " + pin_id + "的pin");
        if (pin.getVisibility() == 1)
            return CommonResult.failed("无法删除公共pin");

        if (!pin.getUser_id().equals(id)) {
            throw new RuntimeException("不要尝试删除他人的地图钉~");
        }

        int photoRet = photoService.deletePhotoByPinId(pin_id);
        String msg = "";
        if (photoRet == 0)
            msg = ("不存在外键p_id = " + pin_id + "的photo ");
        int ret = pinService.deletePinById(pin_id);
        if (ret == 0)
            return CommonResult.failed(msg + "删除id = " + pin_id + "的pin失败");
        else
            return CommonResult.success(msg);
    }

    @GetMapping("/pin/getPinPhotoById/{pin_id}")
    public CommonResult getPinPhotoById(@PathVariable(value = "pin_id", required = false) Integer pin_id) {
        List<String> urls = photoService.getPhotoUrlByPinId(pin_id);
        return CommonResult.success(urls);
    }

    @RequestMapping("/pin_search")
    public CommonResult searchPin(@RequestBody Text text, @RequestParam(required = false, name = "id") Integer id) {
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
        ArrayList<String> photos = photoService.getPhotoUrlByPinId(pin.getId());
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

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/pin/changePinInfoById")
    public CommonResult changePinInfoById(@RequestBody Pin pin,
                                          @RequestParam(name = "id") Integer id) {
        User user = userService.findUserById(id).get(0);

        if (user.getType() != 1 &&
                !pinService.getPinById(pin.getId()).getUser_id().equals(id)) {
            throw new RuntimeException("不要尝试修改他人的地图钉~");
        }

        // pin的可见性由后端设定
        pin.setVisibility(user.getType());

        if (pinService.updatePin(pin) == 1)
            return CommonResult.success(null);
        else
            return CommonResult.failed("数据库不存在id = " + pin.getId() + "的pin");
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
    public CommonResult getUserAllBriefPin(@RequestParam(required = false, name = "id") Integer id) {
        List<PinBriefInfo> briefInfos = new ArrayList<>();
        for (Pin pin : pinService.getUserAllBriefPin(id, 1)) {
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

    @GetMapping("/pin/getAllPublicPin")
    public CommonResult getAllPublicPin() {
        PublicPins publicPins = new PublicPins();
        for (int type = 1; type <= 7; type++) {
            List<Pin> pins = pinService.getAllPublicPin(type);
            switch (type) {
                case 1 -> publicPins.setTag1(pins);
                case 2 -> publicPins.setTag2(pins);
                case 3 -> publicPins.setTag3(pins);
                case 4 -> publicPins.setTag4(pins);
                case 5 -> publicPins.setTag5(pins);
                case 6 -> publicPins.setTag6(pins);
                case 7 -> publicPins.setTag7(pins);
            }
        }
        return CommonResult.success(publicPins);
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping("/switchPos")
    public CommonResult switchPos(@RequestBody Pin pin,
                                  @RequestParam(value = "id") Integer id) {
        if (!pinService.getPinById(pin.getId()).getUser_id().equals(id)) {
            throw new RuntimeException("不要尝试修改他人的地图钉~");
        }

        if (pinService.switchPos(pin) == 0)
            return CommonResult.failed("修改pin位置失败");
        else
            return CommonResult.success("修改pin位置成功");
    }
}
