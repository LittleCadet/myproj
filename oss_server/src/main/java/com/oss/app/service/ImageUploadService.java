package com.oss.app.service;

import com.aliyun.oss.OSS;
import com.oss.app.util.ImageUploadUtil;
import com.oss.app.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * LittleCadet
 */
@Slf4j
@Service
public class ImageUploadService {

    @Value("${spring.oss.bucket_name}")
    private String bucketName;

    @Value("${spring.oss.access_key}")
    private String accessKey;

    @Autowired
    private OSS ossClient;

    public void operation(){

        //生成二维码
        File file =  QRCodeUtil.zxingCodeCreate("http://www.baidu.com","C:\\Users\\Administrator\\Desktop\\sql",200,null);

        //二维码上传到Oss
        ImageUploadUtil.imageUpload(file,ossClient,bucketName);

        String key = "1569584939401sql891.jpg";
        //从OSS下载到本地
        ImageUploadUtil.download(ossClient,bucketName,"C:\\Users\\Administrator\\Desktop\\sql",key);

    }

}
