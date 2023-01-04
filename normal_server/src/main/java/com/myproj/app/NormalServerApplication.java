package com.myproj.app;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.myproj.app.deadlock.DeadThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class NormalServerApplication {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.myproj.app");

		//开始执行死锁代码
//		deadLock();

		SpringApplication.run(NormalServerApplication.class, args);

		System.out.println("启动成功！！！");

//		initFlowRules();
//		while (true) {
//			Entry entry = null;
//			try {
//				entry = SphU.entry("HelloWorld");
//				/*您的业务逻辑 - 开始*/
//				System.out.println("hello world");
//				TimeUnit.SECONDS.sleep(3);
//				/*您的业务逻辑 - 结束*/
//			} catch (BlockException e1) {
//				/*流控逻辑处理 - 开始*/
//				System.out.println("block!");
//				/*流控逻辑处理 - 结束*/
//			} catch (InterruptedException e) {
//				throw new RuntimeException(e);
//			} finally {
//				if (entry != null) {
//					entry.exit();
//				}
//			}
//		}
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


	private static void initFlowRules(){
		List<FlowRule> rules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		rule.setResource("HelloWorld");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 20.
		rule.setCount(20);
		rules.add(rule);
		FlowRuleManager.loadRules(rules);
	}

}
