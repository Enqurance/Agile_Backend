package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Photo;
import com.example.backend.result.CommonResult;
import com.example.backend.service.PhotoService;
import com.example.backend.mapper.PhotoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* @author Sisyphus
* @description 针对表【photo】的数据库操作Service实现
* @createDate 2023-04-11 16:27:14
*/
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo>
    implements PhotoService{
    @Resource
    PhotoMapper photoMapper;

    @Override
    public int insertPhoto(Photo photo) {
        int ret = photoMapper.insertAll(photo);
        return ret;
    }

    @Override
    public ArrayList<String> getPhotoUrlById(Integer id) {
        ArrayList<String> arrayUrl = photoMapper.getPhotoUrlById(id);
        return arrayUrl;
    }

    @Override
    public String getUrlStrById(Integer id) {
        return photoMapper.getPhotoUrlById(id).get(0);
    }

    @Override
    public CommonResult uploadPhoto(MultipartFile pic, String picPath) {
        String picName = pic.getOriginalFilename();
        File file = new File(picPath);
        if (!file.exists())
            file.mkdir();
        Photo photo = new Photo();
        UUID uuid = UUID.randomUUID();
        String url = picPath + picName.replace(".", "_" + uuid + ".");
        photo.setUrl(url);
        try {
            pic.transferTo(new File(url));
            insertPhoto(photo);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("pic储存出现错误");
        }
        return CommonResult.success(null);
    }
}




