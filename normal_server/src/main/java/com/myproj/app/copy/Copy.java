package com.myproj.app.copy;

import org.springframework.beans.BeanUtils;

/**
 * @Description:
 * @Author: shenxie
 * @CreateDate: 2019/10/25
 * @UpdateUser:
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class Copy {

    public void showInfo(){
        CopyA a = CopyA.builder().name("1").age(1).sex(1).build();
        CopyB b = CopyB.builder().build();
        BeanUtils.copyProperties(a,b);
        System.out.println(b);
    }
}
