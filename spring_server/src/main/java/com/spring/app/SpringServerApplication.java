package com.spring.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SpringServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringServerApplication.class, args);
	}

}
