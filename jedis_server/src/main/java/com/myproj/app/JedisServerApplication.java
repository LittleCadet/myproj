package com.myproj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.myproj.app"})
public class JedisServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JedisServerApplication.class, args);
	}

}


