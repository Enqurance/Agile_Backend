package com.example.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.domain.Examine;
import com.example.backend.service.ExamineService;
import com.example.backend.mapper.ExamineMapper;
import com.google.gson.JsonObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
* @author Sisyphus
* @description 针对表【examine】的数据库操作Service实现
* @createDate 2023-05-23 21:33:18
*/
@Service
public class ExamineServiceImpl extends ServiceImpl<ExamineMapper, Examine>
    implements ExamineService{
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
    public void upload(String prefix, String context, String id) {
        if(context == null){
            throw new RuntimeException("上传字符串为空");
        }

        COSClient cosClient = createCosClient();

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        try {
            File localFile = File.createTempFile("temp",".txt");
            FileWriter fileWriter = new FileWriter(localFile);
            fileWriter.write(context);
            fileWriter.flush();
            // 指定要上传到 COS 上的路径
            String key = "/forumText/" + prefix + "/" + id + ".txt";
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            putObjectRequest.getKey();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }finally {
            // 关闭客户端(关闭后台线程)
            cosClient.shutdown();
        }
    }

    @Override
    public void delete(String prefix, String id) {
        String path = "/forumText/" + prefix + "/" + id + ".txt";
        COSClient cosClient = createCosClient();
        try {
            cosClient.deleteObject(bucketName, path);
        } catch (CosClientException e) {
            throw new RuntimeException("删除文本失败");
        } finally {
            cosClient.shutdown();
        }
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




