package com.myproj.app.concurrent;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author shenxie
 * @date 2021/7/27
 */
public class ThreadValue {

    public static void main(String[] args) {
//        testThreadLocal();
//        testInheritableThreadLocal();
//        testTransmittableTHreadLocal();
//        testReactor();
//        while(true){
//            testReactorEntity();
//        }
        while(true){
            testReactorEntity2();
        }
//        testForEach();
    }

    /**
     * threadlocal在线程间传递值时， 不行
     */
    public static void testThreadLocal(){
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("not ok");
        new Thread(()->{
            System.out.println(threadLocal.get());
        }).start();
    }

    /**
     * jdk的Thread的InheritableThreadLocal: 可以实现多线程间的值传递。  【但不能实现线程池的值传递】。
     * 原理： 在使用Thread的无参构造时，会在init阶段，将父线程的ThreadLocal值，赋值给子线程。
     *
     * 特点：
     * 1.子线程可以拿到父线程的值
     * 2.子线程修改不影响父线程的变量的值
     * 3.父线程的值的变更 或 删除 ，不影响子线程的值，
     */
    public static void testInheritableThreadLocal(){
        InheritableThreadLocal<String> itl = new InheritableThreadLocal<>();
        itl.set("father");
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("subThread:" + itl.get());
            itl.set("son");
//            itl.remove();
            System.out.println(itl.get());
        }).start();

        itl.set("changeFather");
//        itl.remove();

        try {
            //等待子线程执行完
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("thread:" + itl.get());
    }

    /**
     * ali的transmittableThreadLocal是InheritableThreadLocal的增强版: 可实现线程池的值传递
     *
     * 特点：
     * 1.子线程可以拿到父线程的值
     * 2.子线程修改不影响父线程的变量的值
     * 3.父线程的值的变更 或 删除 ，不影响子线程的值，
     */
    public static void testTransmittableTHreadLocal(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();

        // 在父线程中设置
        context.set("value-set-in-parent");

        // 额外的处理，生成修饰了的对象ttlRunnable
        TtlRunnable ttlRunnable = TtlRunnable.get(() -> {
            System.out.println("child:" + context.get());
            context.set("childChangeValue");
//            context.remove();
            System.out.println("child:" + context.get());
        });
        executorService.submit(ttlRunnable);

//        context.set("parentChangeValue");
        context.remove();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("parent:" + context.get());
    }

    public static void testReactorString(){
        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        context.set("value");
        Mono.just(1)
                .doOnNext(t -> {
                    System.out.println("子线程：" + Thread.currentThread().getName() + ", 值："  + context.get());
//                    context.remove();
//                    context.set("childValue");
//                    System.out.println("子线程：" + Thread.currentThread().getName() + ", 值："  + context.get());

                })
                .subscribeOn(Schedulers.fromExecutor(new ThreadPoolExecutor(
                        10, 10, 10,
                        TimeUnit.MINUTES,
                        new LinkedBlockingQueue<>(100),
                        new ThreadFactoryBuilder().setNameFormat("biz-thread").build(),
                        new ThreadPoolExecutor.AbortPolicy())))
                .subscribe();

//        context.set("parentValue");

//        context.remove();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父线程：" +Thread.currentThread().getName() + ", 值："  + context.get());

    }

    public static void testReactorEntity(){
        InnerClass innerClass = new InnerClass();
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.setContent("value");
        innerClass.getContext().set(Lists.newArrayList(innerClass2));
        Executor executor = threadPool();
        Mono.just(innerClass)
                .doFirst(() -> {
                    next3(innerClass);
                })
                .doOnNext(t -> {
                    next(t, executor);
//                    innerClass.getContext().remove();
                    InnerClass2 innerClass3 = new InnerClass2();
                    innerClass3.setContent("childValue");
                    innerClass.getContext().set(Lists.newArrayList(innerClass3));
                    System.out.println("子线程：" + Thread.currentThread().getName() + ", 值："  + innerClass.getContext().get());

                })
                .subscribeOn(Schedulers.fromExecutor(executor))
                .subscribe();

//        innerClass.getContext().set("parentValue");

//        innerClass.getContext().remove();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("父线程：" +Thread.currentThread().getName() + ", 值："  +  innerClass.getContext().get());

    }

    public static void testReactorEntity2(){
        InnerClass innerClass = new InnerClass();
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.setContent("value");
        innerClass.getContext().set(Lists.newArrayList(innerClass2));
        Executor executor = threadPool();
//        for (int i = 0; i < 20; i++) {
            CountDownLatch latch = new CountDownLatch(2);
            Mono.just(innerClass)
                    // 获取最小不相似度
                    .doOnNext(ThreadValue::next2)
                    .doFinally(t -> latch.countDown())
                    .subscribeOn(Schedulers.fromExecutor(executor))
                    .subscribe();

            Mono.just(innerClass)
                    // 将记录中的泛化属性的值替换为泛化树的父节点的值
                    .doOnNext(ThreadValue::next3)
                    .doFinally(t -> latch.countDown())
                    .subscribeOn(Schedulers.fromExecutor(executor))
                    .subscribe();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }

        System.out.println("父线程：" +Thread.currentThread().getName() + ", context值："  +  innerClass.getContext().get()  + ", txt值：" + innerClass.getTxt());
    }

    public static void testForEach(){
        InnerClass innerClass = new InnerClass();
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.setContent("value");
        innerClass.getContext().set(Lists.newArrayList(innerClass2));

        Lists.newArrayList(1,2,3).parallelStream().forEach(t -> {
            System.out.println("子线程1：" +Thread.currentThread().getName() + ", 值："  +  innerClass.getContext().get());
            Lists.newArrayList(4,5,6).parallelStream().forEach(d -> {
                System.out.println("子线程2：" +Thread.currentThread().getName() + ", 值："  +  innerClass.getContext().get());
            });
        });

        System.out.println("父线程：" +Thread.currentThread().getName() + ", 值："  +  innerClass.getContext().get());
    }

    public static Executor threadPool(){
        return new ThreadPoolExecutor(
                3, 3, 3,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(100),
                new ThreadFactoryBuilder().setNameFormat("biz-thread").build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public static void next(InnerClass innerClass, Executor executor) {
        System.out.println("next子线程：" + Thread.currentThread().getName() + ", 值：" + innerClass.getContext().get());
        Mono.just(innerClass)
                .doOnNext(ThreadValue::next2)
                .subscribeOn(Schedulers.fromExecutor(executor))
                .doOnSuccess(t -> t.getContext().remove())
                .subscribe();
    }
    public static void next2(InnerClass innerClass) {
        innerClass.setTxt("next2Txt");
        System.out.println("next子线程2：" + Thread.currentThread().getName() + ", context值：" + innerClass.getContext().get() + ", txt值：" + innerClass.getTxt());
    }
    public static void next3(InnerClass innerClass) {
//        List<InnerClass2> tmp = innerClass.getContext().get();
//        tmp.get(0).setContent("value2");
        TransmittableThreadLocal<List<InnerClass2>> contxt = new TransmittableThreadLocal<>();
        InnerClass2 innerClass2 = new InnerClass2();
        innerClass2.setContent("new value2");
        List<InnerClass2> innerClass2s = Lists.newArrayList(innerClass2);
        contxt.set(innerClass2s);
        innerClass.setContext(contxt);
        System.out.println("next子线程3：" + Thread.currentThread().getName() + ", context值：" + innerClass.getContext().get()  + ", txt值：" + innerClass.getTxt());
    }

    @Data
    static class InnerClass{
        private TransmittableThreadLocal<List<InnerClass2>> context = new TransmittableThreadLocal<>();

        private String txt;
    }

    @Data
    static class InnerClass2{
        private String content;
    }
}
