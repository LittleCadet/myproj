package com.myproj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myproj.app"})
@EnableScheduling
public class JedisServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JedisServerApplication.class, args);
	}

}


