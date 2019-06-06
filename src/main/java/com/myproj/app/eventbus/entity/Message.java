package com.myproj.app.eventbus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author The flow developers
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String user;

    private String message;

    private Long date;
}
