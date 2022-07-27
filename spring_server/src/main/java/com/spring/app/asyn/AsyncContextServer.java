package com.spring.app.asyn;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Servlet 3.0 对于异步响应的处理。
 * @author shenxie
 * @date 2022/7/26
 */
@RestController
@RequestMapping("async")
public class AsyncContextServer {

    Boolean flag = false;

    @GetMapping
    public void get(ServletRequest request){
        flag = true;
        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(3000);

        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onComplete(AsyncEvent asyncEvent) throws IOException {
                System.out.println("操作完成:" + Thread.currentThread().getName());
            }

            @Override
            public void onTimeout(AsyncEvent asyncEvent) throws IOException {
                System.out.println("超时返回!!!");
                asyncContext.getResponse().setCharacterEncoding("utf-8");
                asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                asyncContext.getResponse().getWriter().println("超时了！！！!");
                asyncContext.complete();
            }

            @Override
            public void onError(AsyncEvent asyncEvent) throws IOException {
                System.out.println("出现了m某些异常");
                asyncEvent.getThrowable().printStackTrace();

                asyncContext.getResponse().setCharacterEncoding("utf-8");
                asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                asyncContext.getResponse().getWriter().println("出现了某些异常哦！！！!");
                asyncContext.complete();
            }

            @Override
            public void onStartAsync(AsyncEvent asyncEvent) throws IOException {
                System.out.println("开始执行");
            }
        });

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> {
            try {
                HttpServletResponse response = (HttpServletResponse)asyncContext.getResponse();
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                response.setHeader("Cache-Control", "no-cache,no-store");
                response.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().println("===asyncContext 完成!!!");
                asyncContext.complete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 30, TimeUnit.SECONDS);

    }

}
