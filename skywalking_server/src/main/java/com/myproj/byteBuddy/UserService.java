package com.myproj.byteBuddy;

public class UserService {
 
    //方法1
    public String username(){
        System.out.println("com.jokermqc.service.UserService.username.....");
        return "张三";
    }
 
    //方法2
    public String address(String username){
        System.out.println("com.jokermqc.service.UserService.address(String username).....");
        return username+"来自 【湖北省武汉市】";
    }
 
    //方法3
    public String address(String username,String city){
        System.out.println("com.jokermqc.service.UserService.address(String username,String city).....");
        return username+"来自 【湖北省"+city+"】";
    }
}