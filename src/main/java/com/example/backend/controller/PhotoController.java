package com.example.backend.controller;

import com.example.backend.domain.Photo;
import com.example.backend.domain.Pin;
import com.example.backend.domain.User;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PhotoService;
import com.example.backend.service.PinService;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('USER')")
public class PhotoController {
    @Autowired
    PhotoService photoService;
    @Autowired
    UserService userService;
    @Autowired
    PinService pinService;

    @RequestMapping("/photo/insert")
    public CommonResult insertPhoto(@RequestBody Photo photo) {
        int ret = photoService.insertPhoto(photo);
        if (ret == 0)
            return CommonResult.failed("photo数据插入失败");
        else
            return CommonResult.success(null);
    }

    /**
     * 上传道腾讯云服务器（https://cloud.tencent.com/document/product/436/10199）
     * @return
     */
    @RequestMapping("/photo/uploadPinPhoto")
    public CommonResult UploadPinPhoto(@RequestParam(value = "pin_id") Integer pin_id,
                                       @RequestParam(value = "pic") MultipartFile pic) {
        String prefix = "pinPhoto" + "/" + pin_id;
        Pin pin = pinService.getPinById(pin_id);
        if (pin == null)
            return CommonResult.failed("id = " + pin_id + " 的pin不存在");
        String urlPic = photoService.Upload(prefix, pic);
        Photo photo = new Photo();
        photo.setPin_id(pin_id);
        photo.setUrl(urlPic);
        int ret = photoService.insertPhoto(photo);
        if (ret == 0)
            return CommonResult.failed("photo数据插入失败");
        else
            return CommonResult.success("地图钉photo上传成功");
    }

    @RequestMapping("/photo/uploadUserIcon")
    public CommonResult UploadUserIcon(@RequestParam(value = "id") Integer id,
                                       @RequestParam(value = "pic") MultipartFile pic) {
        String prefix = "userIcon" + "/" + id;
        List<User> users = userService.findUserById(id);
        if (users.size() == 0)
            return CommonResult.failed("id = " + id + " 的user不存在");
        String urlPic = photoService.Upload(prefix, pic);
        User user = users.get(0);
        if (!user.getIcon().equals("https://agile-pic-1313874439.cos.ap-beijing.myqcloud.com/agile-pic/win.jpg"))
            photoService.delete(user.getIcon());
        user.setIcon(urlPic);
        int ret = userService.updateIcon(user);
        if (ret == 0)
            return CommonResult.failed("icon数据插入失败");
        else
            return CommonResult.success(urlPic);
    }

    @RequestMapping("/photo/deletePinPhoto")
    public CommonResult deletePinPhoto(@RequestBody Photo photo) {
        int ret = photoService.deletePhotoByUrl(photo.getUrl(), photo.getPin_id());
        if (ret == 0) {
            return CommonResult.failed("删除图片失败或图片不存在");
        } else {
            photoService.delete(photo.getUrl());
            return CommonResult.success("删除图片成功");
        }
    }
}
