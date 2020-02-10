package com.springcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.springcache","com.myproj.app"})
public class SpringCacheServerApplication
{
	public static void main(String[] args) {
		SpringApplication.run(SpringCacheServerApplication.class, args);
	}

}
