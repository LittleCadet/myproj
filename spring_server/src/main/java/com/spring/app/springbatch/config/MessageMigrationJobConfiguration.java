package com.spring.app.springbatch.config;

import com.spring.app.springbatch.core.MessageItemReadListener;
import com.spring.app.springbatch.core.MessageLineMapper;
import com.spring.app.springbatch.core.MessageProcessor;
import com.spring.app.springbatch.core.MessageWriteListener;
import com.spring.app.springbatch.entity.Message;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.function.Function;

/**
 * @author shenxie
 * @date 2020/10/5
 */
public class MessageMigrationJobConfiguration {

    /**
     * chunk: 读取一行处理一条数据，但是累计扫CHUNK_SIZE后， 那么触发写的动作
     */
    private static final Integer CHUNK_SIZE = 2;

    /**
     * skip机制：
     * 1.遇到报错，跳过 ， 不然整个job会挂掉。
     * 2.遇到什么报错 ， 跳过多少条数据。 均需指定
     * 3.如果失败的数据超过skipLimit , 那么也会报错
     */
    private static final Integer SKIP_LIMIT = 1;

    private static final String MESSAGE_READER_FILE = "spring_server/src/main/resources/source.txt";
    private static final String MESSAGE_WRITER_FILE = "spring_server/src/main/resources/target.txt";

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job messageMigrationJob(@Qualifier("messageMigrationStep") Step messageMigrationStep) {
        return jobBuilderFactory.get("messageMigrationJob")
                .start(messageMigrationStep)
                .build();
    }

    /**
     * 定义 reader
     * @return
     */
    @Bean
    public FlatFileItemReader<Message> jsonMessageReader() {
        FlatFileItemReader<Message> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource(new File(MESSAGE_READER_FILE)));
        reader.setLineMapper(new MessageLineMapper());
        return reader;
    }

    /**
     * 定义writer
     * @return
     */
    @Bean
    public FlatFileItemWriter<Message> messageItemWriter(){
        FlatFileItemWriter<Message> writer = new FlatFileItemWriter<>();
        writer.setEncoding("utf-8");
        writer.setResource(new FileSystemResource(new File(MESSAGE_WRITER_FILE)));
        writer.setLineAggregator(new LineAggregator<Message>() {
            @Override
            public String aggregate(Message item) {
                System.out.println("writter中LineAggregator ： item : " + item);
                return item.toString();
            }
        });
        return writer;
    }

    @Bean
    public Writer errorWriter(){
        Writer writer = new Writer() {
            @Override
            public void write(char[] cbuf, int off, int len) throws IOException {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public void close() throws IOException {

            }
        };

        return writer;
    }


    /**
     * 初始化step ： chunk 【 reader + processor + writer + listener】
     * @param jsonMessageReader
     * @param messageItemWriter
     * @return
     */
    @Bean
    public Step messageMigrationStep(@Qualifier("jsonMessageReader") FlatFileItemReader<Message> jsonMessageReader,
                                     @Qualifier("messageItemWriter") FlatFileItemWriter<Message> messageItemWriter,
                                     @Qualifier("messageProcessor") MessageProcessor messageProcessor) {
        return stepBuilderFactory.get("messageMigrationStep")
                .<Message, Message>chunk(CHUNK_SIZE)
                .reader(jsonMessageReader).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageItemReadListener())
                .processor(messageProcessor)
                .writer(messageItemWriter).faultTolerant().skip(Exception.class).skipLimit(SKIP_LIMIT)
                .listener(new MessageWriteListener())
                .build();
    }


}
