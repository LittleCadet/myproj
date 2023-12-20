package com.myproj.app.ingress;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2023/7/28
 */
@RestController
public class IngressDemo {

    @GetMapping("冒泡排序/v1")
    public String testV1() {
        return "ingress success!!! canary deployment success!!! /冒泡排序/v1\n";
    }

    @GetMapping("冒泡排序/v2")
    public String testV2() {
        return "ingress success!!! canary deployment success!!! /冒泡排序/v2\n";
    }
}
