package com.metric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shenxie
 **/
@SpringBootApplication
public class MetricApplication {
    public static void main(String[] args) {
        SpringApplication.run(MetricApplication.class);
        new HTTPServer().Start();
    }
}
