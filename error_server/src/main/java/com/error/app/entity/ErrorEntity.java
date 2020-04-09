package com.error.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LittleCadet
 * @Date 2020/3/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorEntity
{
    private String name;
}
