package com.myproj.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shenxie
 * @date 2022/8/1
 */
@RestController
@RequestMapping("sentinel")
public class SentinelController {

    /**
     * from normal1: gatewayType：
     * from normal2: gatewayType：
     * from normal3: gatewayType
     * @param request
     * @return
     */
    @GetMapping("get")
    public String showInfo(HttpServletRequest request) {
//        String gatewayType = request.getHeader("gatewayType");
//        return "sentinel test succeed --- from normal3: gatewayType:" + gatewayType;
        return "sentinel test succeed --- from normal3: gatewayType:" + "gateway-merge";
    }
}
