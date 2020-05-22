package com.spring.app.springpublish.event;

import lombok.ToString;
import org.springframework.context.ApplicationEvent;

/**
 * @author shenxie
 * @date 2020/5/21
 */
@ToString
public class MessageEvent extends ApplicationEvent {

    private String message;

    private String code;

    /**
     * 任何一个构造器，强制使用上下文，否则这个事件不能注入到spring中
     * @param source
     */
    public MessageEvent(Object source) {
        super(source);
    }

    /**
     * 任何一个构造器，强制使用上下文，否则这个事件不能注入到spring中
     * @param source
     */
    public MessageEvent(Object source, String message, String code) {
        super(source);
        this.message = message;
        this.code = code;
    }

}
