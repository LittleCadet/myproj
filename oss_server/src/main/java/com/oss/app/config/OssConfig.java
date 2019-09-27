package com.oss.app.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * LittleCadet
 */
@Slf4j
@Configuration
public class OssConfig {
    @Value("${spring.oss.bucket_name}")
    private String bucketName;

    @Value("${spring.oss.endpoint}")
    private String endPoint;

    @Value("${spring.oss.access_key}")
    private String accessKey;

    @Value("${spring.oss.access_secret}")
    private String accessSecret;

    @Bean
    public OSS ossClient(){

        log.info("=============start to init oss ==============");

        log.info("bucketName:{},endPoint:{},accessKey:{},accessSecret:{}",bucketName,endPoint,accessKey,accessSecret);

        OSS ossClient =new OSSClientBuilder().build(endPoint, accessKey, accessSecret);

        log.info("=============succeed to init oss ==============");

        return ossClient;
    }

}
