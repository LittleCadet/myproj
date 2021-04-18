package com.myproj.app.algorithm.heuristic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author shenxie
 * @date 2021/4/13
 */
@Data
@AllArgsConstructor
public class AlertDto {

    private String host;

    private String exception;

    private String message;

    @Override
    public boolean equals(Object o) {

        if (o instanceof AlertDto) {
            AlertDto alertDto = (AlertDto) o;
            return host.equals(alertDto.getHost()) && exception.equals(alertDto.getException());
        }

        return false;
    }

}
