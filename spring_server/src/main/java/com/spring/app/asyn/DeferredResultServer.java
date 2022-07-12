package com.spring.app.asyn;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * callable 与 DeferredResult：
 * 1. 共同点：
 *  a. 都用于springServlet的异步通信。
 *  b. 都用于第一时间返回服务端的线程。 【filter  + interceptor会关闭，  但是response开启】
 * 2. 不同点：
 *   后者： 可以替代前者： 原因： 后者： 扩展性更好： 更利于将其他线程产生的结果 与 SpringServlet的响应绑定在一起。
 *
 * @author shenxie
 * @date 2022/7/12
 */
@RequestMapping("server")
@RestController
public class DeferredResultServer implements InitializingBean {

    Boolean flag = false;

    Map<String, DeferredResult<String>> deferredResultMap = Maps.newHashMap();


    @GetMapping("deferred")
    public DeferredResult<String> getResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>(3000L);
        deferredResultMap.put("key", deferredResult);
        deferredResult.onCompletion(() -> {
            System.out.println("===onCompletion");
        });
        deferredResult.onTimeout(() -> {
            System.out.println("===onTimeout");
        });
        deferredResult.onError(t -> {
            System.out.println("===onError:" + t);
        });
//        deferredResult.setResultHandler(t -> {
//            if("deferredResult !!!".equals(deferredResult.getResult())) {
//                System.out.println("===处理成功");
//            }
//            System.out.println("===处理完成");
//        });
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        flag = true;
        return deferredResult;
    }

    @GetMapping("callable")
    public Callable<String> getResultV2() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(2);
                return "callable!!!";
            }
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        new Thread(() -> {
            while (true) {
                if(flag) {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                        deferredResultMap.get("key").setResult("deferredResult !!!");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        System.out.println("======DeferredResultServer:start");
    }
}

