package com.oss.app.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * LittleCadet
 */
@Slf4j
public class ImageUploadUtil {



    /**
     * 文件上传接口
     */
    public static void imageUpload(File file,OSS ossClient,String bucketName){

        String fileName = System.currentTimeMillis() + file.getName();
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            PutObjectResult result =  ossClient.putObject(bucketName,fileName,is);

            log.info("succeed to upload image !");

            //return result.getResponse().getUri();
        } catch (FileNotFoundException e) {
            log.error("filed to upload image ! e:{}",e.getMessage());
        } finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("failed to close inputStream !");
                }
            }
        }

        //return null;
    }

    /**
     * 从oss下载
     * @param ossClient
     * @param bucketName
     * @param localPath
     */
    public static void download(OSS ossClient,String bucketName,String localPath,String accessKey){
        ossClient.getObject(new GetObjectRequest(bucketName, accessKey), new File(localPath));
    }

}
