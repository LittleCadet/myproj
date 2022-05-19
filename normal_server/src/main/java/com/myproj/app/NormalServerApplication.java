package com.myproj.app;

import com.myproj.app.deadlock.DeadThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
/*@ComponentScan("com.myproj.app.mapper")*/
public class NormalServerApplication {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.myproj.app");

		//开始执行死锁代码
//		deadLock();

		SpringApplication.run(NormalServerApplication.class, args);
	}

	public static void deadLock(){
		DeadThread dt1 = new DeadThread();
		dt1.setFlag("a");
		Thread t1 = new Thread(dt1);
		t1.start();

		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		dt1.setFlag("b");
		Thread t2 = new Thread(dt1);

		t2.start();
	}

}
