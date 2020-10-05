package com.spring.app.springbatch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author shenxie
 * @date 2020/10/5
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String name;

    private String sex;
}

