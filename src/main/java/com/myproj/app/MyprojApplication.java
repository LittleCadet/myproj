package com.myproj.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
public class MyprojApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyprojApplication.class, args);
	}

}
