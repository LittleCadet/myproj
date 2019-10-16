package com.oss.app.service;

import com.alibaba.fastjson.util.IOUtils;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.oss.app.util.ImageUploadUtil;
import com.oss.app.util.QRCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

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


    public String operation(){

        String result = null;

        //生成二维码
        //File file =  QRCodeUtil.zxingCodeCreate("http://47.99.112.38:8090/image/judge","/usr/portal/image/",200,"C:\\Users\\Public\\Pictures\\Sample Pictures\\7.2kb.jpg");

        //解析二维码
        File file = new File("C:\\Users\\Administrator\\Desktop\\1012.jpg");

        //二维码上传到Oss
        /*result = ImageUploadUtil.imageUpload(file,ossClient,bucketName);
        System.out.println("ossPath:" + result);*/

        //String key = "1569584939401sql891.jpg";
        //从OSS下载到本地
        //ImageUploadUtil.download(ossClient,bucketName,"C:\\Users\\Administrator\\Desktop\\sql",key);
        //String result = null;
        return (null != result?"success":"fail");

    }

}
