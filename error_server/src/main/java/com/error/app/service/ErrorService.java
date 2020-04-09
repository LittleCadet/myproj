package com.error.app.service;

import com.error.app.entity.ErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author LittleCadet
 * @Date 2020/3/25
 */
@Slf4j
@Service
public class ErrorService
{

    public void showInfo(){
        ErrorEntity errorEntity = null;
        System.out.println(errorEntity.getName());
    }
}
