package com.spring.app.annotation.method;

import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author shenxie
 * @date 2021/2/5
 */
public class MethodHandlerTest {


    public static void main(String[] args) {
//        simpleInvokeMethod();
        complexInvokeMethod();

    }

    /**
     * 反射： 在指定路径下，扫描含有指定注解的方法，之后完成对该方法的调用
     */
    public static void simpleInvokeMethod(){
        Reflections reflection = new Reflections("com.spring.app.annotation.method", MethodAnnotationsScanner.class);
        Set<Method> methodsAnnotatedWith = reflection.getMethodsAnnotatedWith(MethodHandler.class);
        methodsAnnotatedWith.forEach(method -> {
            System.out.println(method.getName());
            try {
                // 反射的invoke：需要使用该方法的对象的实例 + 参数
                Object invoke = method.invoke(method.getDeclaringClass().newInstance());
                System.out.println(invoke);
            } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                e.printStackTrace();
            }
        });
    }

    @SneakyThrows
    public static void complexInvokeMethod(){
        try {
            Method showInfo = AnnotationDemo.class.getMethod("showInfo2", Map.class);
            if( ! ObjectUtils.isEmpty(showInfo)){
                boolean annotationPresent = showInfo.isAnnotationPresent(MethodHandler.class);
                System.out.println("指定方法是否含有该注解：" + annotationPresent);
            }
        }catch (NoSuchMethodException e){
            System.out.println("该方法不含有指定注解");
        }
        Method[] methods = AnnotationDemo.class.getMethods();
        AnnotationDemo finalAnnotationDemo = AnnotationDemo.class.newInstance();;
        Arrays.stream(methods).forEach(method -> {
            MethodHandler annotation = method.getAnnotation(MethodHandler.class);
            if( !ObjectUtils.isEmpty(annotation)) {
                System.out.println(annotation);
                // 匹配到含有指定注解的方法
                if("superHandler".equals(annotation.name())){
                    Map<String,Object> map = Maps.newHashMap();
                    map.put("succeed", "yeah,  u succeed!");
                    try {
                        // 完成对指定方法的调用
                        Object invoke = method.invoke(finalAnnotationDemo, map);
                        System.out.println(invoke);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
