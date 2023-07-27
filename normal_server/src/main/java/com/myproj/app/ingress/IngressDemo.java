package com.myproj.app.ingress;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shenxie
 * @date 2023/7/28
 */
@RestController
public class IngressDemo {

    @GetMapping("test/v1")
    public String testV1() {
        return "ingress success!!! /test/v1";
    }

    @GetMapping("test/v2")
    public String testV2() {
        return "ingress success!!! /test/v2";
    }
}
