package com.spring.app.springbatch.core;

import com.spring.app.springbatch.entity.Message;
import lombok.SneakyThrows;
import org.springframework.batch.core.ItemReadListener;

import java.io.Writer;

import static java.lang.String.format;

/**
 * @author shenxie
 * @date 2020/10/5
 */
public class MessageItemReadListener implements ItemReadListener<Message> {
    private Writer errorWriter;

    @Override
    public void beforeRead() {
        System.out.println("MessageItemReadListener beforeRead ");
    }

    @Override
    public void afterRead(Message item) {

        System.out.println("MessageItemReadListener afterRead ");
    }

    @SneakyThrows
    @Override
    public void onReadError(Exception ex) {
        errorWriter.write(format("%s%n", ex.getMessage()));
    }
}

