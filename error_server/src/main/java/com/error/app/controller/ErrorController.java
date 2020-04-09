package com.error.app.controller;

import com.error.app.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author LittleCadet
 * @Date 2020/3/25
 */
@RestController
@RequestMapping("/error")
public class ErrorController
{
    @Autowired
    private ErrorService errorService;

    @GetMapping("/showInfo")
    public void showInfo(){
        errorService.showInfo();
    }
}
