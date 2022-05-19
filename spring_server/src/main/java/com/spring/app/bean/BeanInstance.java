package com.spring.app.bean;

import org.springframework.context.SmartLifecycle;

/**
 * bean的生命周期：
 *
 * （1）通过无参构造器创建 bean 实例
 *
 *
 * （2）调用属性 setter 方法为 bean 的属性设置值
 *
 *
 * （3）把 bean 实例传递 bean 后置处理器的方法 postProcessBeforeInitialization
 *
 *
 * （4）调用 bean 的初始化的方法（需要配置初始化的方法)
 *
 *
 * （5）把 bean 实例传递 bean 后置处理器的方法 postProcessAfterInitialization
 *
 *
 * （6）获取使用已经创建的 bean
 *
 *
 * （7）当容器关闭时候，调用 bean 的销毁的方法（需要配置销毁的方法)
 *
 *
 *
 * @author shenxie
 * @date 2022/5/19
 */
public class BeanInstance  {

    private String orderNo;

    public BeanInstance() {
        System.out.println("=====无参构造");
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        System.out.println("=====setter");
    }

    private void init(){
        System.out.println("=====init method");
    }

    private void destroy(){
        System.out.println("=====destroy method");
    }
}
