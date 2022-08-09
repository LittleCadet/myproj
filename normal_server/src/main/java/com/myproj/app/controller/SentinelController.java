package com.myproj.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2022/8/1
 */
@RestController
@RequestMapping("sentinel")
public class SentinelController {

    @GetMapping("get")
    public String showInfo() {
        return "sentinel test succeed";
    }
}
