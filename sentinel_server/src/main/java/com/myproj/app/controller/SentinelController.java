package com.myproj.app.controller;

import com.myproj.app.service.PrometheusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author shenxie
 * @date 2022/8/1
 */
@RestController
@RequestMapping("sentinel")
public class SentinelController {

    private final PrometheusService prometheusService;

    public SentinelController(PrometheusService prometheusService) {
        this.prometheusService = prometheusService;
    }

    @GetMapping("get")
    public String showInfo() {
//        try {
//            prometheusService.processRequest();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return "sentinel test succeed";
    }
}
