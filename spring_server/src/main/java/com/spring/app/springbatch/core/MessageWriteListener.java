package com.spring.app.springbatch.core;

import com.spring.app.springbatch.entity.Message;
import lombok.SneakyThrows;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Writer;
import java.util.List;

import static java.lang.String.format;

/**
 * @author shenxie
 * @date 2020/10/5
 */
public class MessageWriteListener implements ItemWriteListener<Message> {

    @Autowired
    private Writer errorWriter;

    @Override
    public void beforeWrite(List<? extends Message> items) {
        System.out.println("ItemWriteListener . beforeWrite ");
    }

    @Override
    public void afterWrite(List<? extends Message> items) {
        System.out.println("ItemWriteListener . afterWrite ");
    }

    @SneakyThrows
    @Override
    public void onWriteError(Exception exception, List<? extends Message> items) {
        System.out.println("ItemWriteListener . onWriteError start");
        errorWriter.write(format("%s%n", exception.getMessage()));
        for (Message message : items) {
            errorWriter.write(format("Failed writing message : %s", message.toString()));
        }

        System.out.println("ItemWriteListener . onWriteError end");
    }
}

