package com.myproj.controller;

import java.io.IOException;
import javax.annotation.PostConstruct;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2025/11/24
 */
@RestController
@RequestMapping("test")
public class Controller2 {

    private OkHttpClient httpClient;

    @PostConstruct
    public void init() {

        httpClient = new OkHttpClient();
    }

    @GetMapping("rpc3")
    public String rpc() throws IOException {
        return "ok3";
    }
}
