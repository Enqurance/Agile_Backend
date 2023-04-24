package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Photo;
import com.example.backend.service.PhotoService;
import com.example.backend.mapper.PhotoMapper;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Value("${spring.tengxun.accessKey}")
    private String accessKey;
    @Value("${spring.tengxun.secretKey}")
    private String secretKey;
    @Value("${spring.tengxun.bucket}")
    private String bucket;
    @Value("${spring.tengxun.bucketName}")
    private String bucketName;
    @Value("${spring.tengxun.path}")
    private String path;

    @Override
    public Integer insertPhoto(Photo photo) {
        return photoMapper.insertAll(photo);
    }

    @Override
    public ArrayList<String> getPhotoUrlByPinId(Integer pin_id) {
        ArrayList<String> arrayUrl = photoMapper.getPhotoUrlByPinId(pin_id);
        return arrayUrl;
    }

    @Override
    public String getUrlStrById(Integer id) {
        return photoMapper.getPhotoUrlByPinId(id).get(0);
    }

    @Override
    public String Upload(String prefix, MultipartFile file) {
        if(file == null){
            throw new RuntimeException("上传图片为空");
        }
        String oldFileName = file.getOriginalFilename();
        assert oldFileName != null;
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + eName;

        COSClient cosClient = createCosClient();

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp",null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "/" + prefix + "/" + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            return (this.path + putObjectRequest.getKey());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            // 关闭客户端(关闭后台线程)
            cosClient.shutdown();
        }
    }

    @Override
    public void delete(String path) {
        String str = path.replace("https://agile-pic-1313874439.cos.ap-beijing.myqcloud.com", "");
        COSClient cosClient = createCosClient();
        try {
            cosClient.deleteObject(bucketName, str);
        } catch (CosClientException e) {
            throw new RuntimeException("删除图片失败");
        } finally {
            cosClient.shutdown();
        }
    }

    @Override
    public int deletePhotoByPinId(Integer pin_id) {
        List<String> urls = photoMapper.getPhotoUrlByPinId(pin_id);
        int ret = photoMapper.deletePhotoByPinId(pin_id);
        for (String url : urls) {
            delete(url);
        }
        return ret;
    }

    @Override
    public int deletePhotoByUrl(String url, Integer pin_id) {
        return photoMapper.deletePhotoByUrl(url, pin_id);
    }

    private COSClient createCosClient() {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosClient = new COSClient(cred, clientConfig);

        return cosClient;
    }
}




