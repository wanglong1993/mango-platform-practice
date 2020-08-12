package cn.siques.mangosound.config;

import cn.siques.mangosound.config.property.OssProperty;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OssConfig {
    @Value("${spring.oss.endpoint}")
    String endpoint;

    @Value("${spring.oss.accessKeyId}")
    String accessKeyId;

    @Value("${spring.oss.accessKeySecret}")
    String accessKeySecret;

    @Value("${spring.oss.bucketName}")
    String bucketName;

    private OSS getOssClient(){
        return  new OSSClientBuilder()
                .build(endpoint, accessKeyId, accessKeySecret);
    }

    public OSSObject getOssObject(String objectName)   {
        OSS   ossClient = getOssClient();
        OSSObject    ossObject = ossClient.getObject(bucketName, objectName);
        return ossObject;
    }
}
