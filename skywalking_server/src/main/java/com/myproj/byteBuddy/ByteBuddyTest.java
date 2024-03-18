package com.myproj.byteBuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author shenxie
 * @date 2024/3/12
 */
public class ByteBuddyTest {

    public static void main(String[] args) throws Exception {
        //创建ByteBuddy
        UserService userService = new ByteBuddy()
                //指定创建UserServiceImpl对象的子类
                .subclass(UserService.class)
                //匹配方法,所有方法均被拦截
                .method(ElementMatchers.isDeclaredBy(UserService.class))
                //任何拦截都返回一个固定值
                .intercept(MethodDelegation.to(new AspectLog()))
                //创建动态对象
                .make()
                //  类的加载策略：
                //  WRAPPER 策略：创建一个新的 ClassLoader 来加载动态生成的类型。
                //  CHILD_FIRST 策略：创建一个子类优先加载的 ClassLoader ，即打破了双亲委派模型。
                //  INJECTION 策略：使用反射将动态生成的类型直接注入到当前 ClassLoader 中。
                .load(ByteBuddy.class.getClassLoader(),
                        ClassLoadingStrategy.Default.INJECTION)
                .getLoaded()
                .newInstance();

        userService.username();
        userService.address("王五","武汉");
        userService.address("张三");
    }
}
