package com.myproj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RocketmqServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RocketmqServerApplication.class, args);
	}

}
