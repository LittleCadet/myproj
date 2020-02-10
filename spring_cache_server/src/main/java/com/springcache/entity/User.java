package com.springcache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author LittleCadet
 * @Date 2020/2/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User
{
    private String id ;

    private String name ;

    private String age ;
}
