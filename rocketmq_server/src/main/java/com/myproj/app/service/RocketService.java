package com.myproj.app.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author LittleCadet
 * @Date 2019/8/15
 */
@Slf4j
@Service
public class RocketService
{
    public void getMwssage(String text,String tags){
        log.info("receipt message:text:" + text + ",tags:" + tags);
    }
}
