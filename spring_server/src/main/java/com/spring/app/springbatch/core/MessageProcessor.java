package com.spring.app.springbatch.core;

import com.spring.app.springbatch.entity.Message;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;


/**
 *
 * 定义processor
 *
 * @author shenxie
 * @date 2020/10/5
 */
@Service
public class MessageProcessor implements ItemProcessor {

    @Override
    public Object process(Object item) throws Exception {
        System.out.println(String.format("process item : %s" , item));
        return item;
    }
}
