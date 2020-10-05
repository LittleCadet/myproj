package com.spring.app.springbatch.core;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.spring.app.springbatch.entity.Message;
import org.springframework.batch.item.file.LineMapper;

import java.util.Map;

/**
 * @author shenxie
 * @date 2020/10/5
 */
public class MessageLineMapper implements LineMapper<Message> {
    private MappingJsonFactory factory = new MappingJsonFactory();

    private static final String SEPARATOR = ",";

    @Override
    public Message mapLine(String line, int lineNumber) throws Exception {
        System.out.println("LineMapper start");
        String[] splits = line.split(SEPARATOR);
        Message message = new Message();
        message.setName(splits[0]);
        message.setSex(splits[1]);
        System.out.println("LineMapper end");
        return message;
    }
}

