package com.spring.app.springbatch;

import com.google.common.collect.Lists;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

import static java.lang.String.format;

/**
 * @author shenxie
 * @date 2020/10/5
 */
@Configuration
public class SpringBatchRun implements ApplicationRunner {

    /**
     * 普通 批处理
     */
    private static final String JOB_NAME_V1 = "messageMigrationJob";

    /**
     * 分片 批处理
      */
    private static final String JOB_NAME_V2 = "messageMigrationJobV2";
    /**
     * 分片 批处理
      */
    private static final String JOB_NAME_V3 = "messageMigrationJobV3";

    private static List<String> JOB_NAMES = Lists.newArrayList();

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobLauncher jobLauncher;

    static{
        //JOB_NAMES.add(JOB_NAME_V1);
        JOB_NAMES.add(JOB_NAME_V2);
        JOB_NAMES.add(JOB_NAME_V3);
    }


    /**
     * 该springBatch的任务 ： 将source.txt的内容 转换为message对象， 并写入target.txt中
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("===============spring batch start===========");
        JOB_NAMES.forEach(this::process);

        Thread.currentThread().join();
        System.out.println("================spring batch end===========");
    }


    private static JobParameters createJobParams() {
        return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
    }

    private void process(String JOB_NAME){
        try {
            Job job = jobRegistry.getJob(JOB_NAME);
            JobExecution jobExecution = jobLauncher.run(job, createJobParams());
            if (!jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
                throw new RuntimeException(format("%s Job execution failed.", JOB_NAME));
            }
        } catch (Exception e) {
            throw new RuntimeException(format("%s Job execution failed.e ： %s", JOB_NAME , e));
        }
    }

}
