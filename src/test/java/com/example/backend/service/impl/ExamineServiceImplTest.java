package com.example.backend.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
class ExamineServiceImplTest {
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

    @InjectMocks
    ExamineServiceImpl examineServiceImpl;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(examineServiceImpl, "accessKey", accessKey);
        ReflectionTestUtils.setField(examineServiceImpl, "secretKey", secretKey);
        ReflectionTestUtils.setField(examineServiceImpl, "bucket", bucket);
        ReflectionTestUtils.setField(examineServiceImpl, "bucketName", bucketName);
        ReflectionTestUtils.setField(examineServiceImpl, "path", path);
    }

    @Test
    void testUpload() {
        examineServiceImpl.upload("prefix", "context", "id");
    }

    @Test
    void testDelete() {
        examineServiceImpl.delete("prefix", "id");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme