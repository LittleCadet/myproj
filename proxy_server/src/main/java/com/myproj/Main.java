package com.myproj;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author shenxie
 * @date 2024/3/20
 */
public class Main {
    /**
     * 通过cglib增强的类： 是通过生成子类的方式完成增强，
     * 增强后的类名： 原类的全限定名&&EnhancerByCGLIB&&唯一标识
     */
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("method:" + method.getName());
                return methodProxy.invokeSuper(o, args);
            }
        });

        Test proxy = (Test) enhancer.create();
        proxy.process();
    }
}