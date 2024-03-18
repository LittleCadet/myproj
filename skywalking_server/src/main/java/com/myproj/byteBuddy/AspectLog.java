package com.myproj.byteBuddy;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;

public class AspectLog {
 
    @RuntimeType
    public Object intercept(
            // 目标对象
            @This Object obj,
            // 注入目标方法的全部参数
            @AllArguments Object[] allArguments,
            // 调用目标方法，必不可少
            @SuperCall Callable<?> zuper,
            // 目标方法
            @Origin Method method,
            // 目标对象
            @Super Object instance
    ) throws Exception {
        //目标方法执行前执行日志记录
        System.out.println("准备执行Method="+method.getName());
        // 调用目标方法
        Object result = zuper.call();
        //目标方法执行后执行日志记录
        System.out.println("方法执行完成Method="+method.getName());
        return result;
    }
}