package com.spring.app.publish.selfpublish.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义event
 * @author shenxie
 * @date 2023/6/19
 */
@Data
@AllArgsConstructor
public class SelfMessageEvent {

    private String message;

    private Integer code;

}
