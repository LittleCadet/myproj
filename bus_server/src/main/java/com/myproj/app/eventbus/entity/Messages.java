package com.myproj.app.eventbus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author LittleCadet
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Messages {

    private DeferredResult<String> output;

    private String user;

    private String message;

    private Long date;
}
