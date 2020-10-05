package com.spring.app.springbatch;

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

import static java.lang.String.format;

/**
 * @author shenxie
 * @date 2020/10/5
 */
@Configuration
public class SpringBatchRun implements ApplicationRunner {
    
    private static final String JOB_NAME = "messageMigrationJob";

    @Autowired
    private JobRegistry jobRegistry;

    @Autowired
    private JobLauncher jobLauncher;


    
    @Override
    public void run(ApplicationArguments args) throws Exception {
            try {
                System.out.println("===============spring batch start===========");
                Job job = jobRegistry.getJob(JOB_NAME);
                JobExecution jobExecution = jobLauncher.run(job, createJobParams());
                if (!jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
                    throw new RuntimeException(format("%s Job execution failed.", JOB_NAME));
                }
                System.out.println("================spring batch end===========");
            } catch (Exception e) {
                throw new RuntimeException(format("%s Job execution failed.", JOB_NAME));
            }
    }


    private static JobParameters createJobParams() {
        return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
    }
}
