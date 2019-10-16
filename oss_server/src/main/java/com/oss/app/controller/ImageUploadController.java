package com.oss.app.controller;

import com.oss.app.service.ImageUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * LittleCadet
 */
@Slf4j
@RequestMapping("/image")
@RestController
public class ImageUploadController {

    @Autowired
    private ImageUploadService imageUploadService;

    /**
     * 将生成的二维码上传Oss
     * @return
     */
    @GetMapping("/upload")
    public String  upload(){
        return imageUploadService.operation();
    }

    /**
     * 区分微信，支付宝客户端的扫码
     * @param request
     * @return
     */
    @GetMapping("/judge")
    public String judge(HttpServletRequest request){
        log.info("request:{}",request);
        String params = request.getHeader("User-Agent").toLowerCase();
        if(params.contains("micromessenger")){
            log.info("weChat client");
            return "wechat client";
        }else if(params.contains("alipayclient")){
            log.info("ali client");
            return "ali client";
        }

        return "error page";
    }

}
