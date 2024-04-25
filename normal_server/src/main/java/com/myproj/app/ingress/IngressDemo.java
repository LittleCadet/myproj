package com.myproj.app.ingress;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngressDemo {

    @GetMapping("/test/v1")
    public String showInfoV1() {
        return "ingress success: /test/v1";
    }

    @GetMapping("/test/v2")
    public String showInfoV2() {
        return "ingress success: /test/v2";
    }

}
